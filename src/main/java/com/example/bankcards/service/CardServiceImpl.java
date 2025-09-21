package com.example.bankcards.service;


import com.example.bankcards.dto.BlockRequestDTO;
import com.example.bankcards.dto.CardDTO;
import com.example.bankcards.entity.BlockRequest;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.Status;
import com.example.bankcards.entity.User;
import com.example.bankcards.repository.BlockRequestRepository;
import com.example.bankcards.repository.CardRepository;
import com.example.bankcards.repository.UserRepository;
import com.example.bankcards.util.BlockRequestMapper;
import com.example.bankcards.util.CardMapper;
import com.example.bankcards.util.EncryptionUtil;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final UserRepository userRepository;
    private final BlockRequestRepository blockRequestRepository;
    private final BlockRequestMapper blockRequestMapper;
    private final EncryptionUtil encryptionUtil;

    public CardServiceImpl(CardRepository cardRepository, CardMapper cardMapper, UserRepository userRepository, BlockRequestRepository blockRequestRepository, BlockRequestMapper blockRequestMapper, EncryptionUtil encryptionUtil) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
        this.userRepository = userRepository;
        this.blockRequestRepository = blockRequestRepository;
        this.blockRequestMapper = blockRequestMapper;
        this.encryptionUtil = encryptionUtil;
    }

    @Override
    @Transactional
    public CardDTO createCard(CardDTO cardDTO) {
        if(cardRepository.existsByNumber(cardDTO.getNumber())) {
            throw new RuntimeException("Card with number " + cardDTO.getNumber() + " already exists");
        }
        Card card = cardMapper.toCard(cardDTO);
        User user = userRepository.findById(cardDTO.getOwner().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        card.setOwner(user);
        card.setNumber(encryptionUtil.encrypt(cardDTO.getNumber()));
        Card savedCard = cardRepository.save(card);
        return cardMapper.toCardDTO(savedCard);
    }

    @Override
    public CardDTO updateCard(CardDTO cardDTO) {
        return cardMapper.toCardDTO(cardRepository.save(cardMapper.toCard(cardDTO)));
    }

    @Override
    public CardDTO getCardById(Long cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        return cardMapper.toCardDTO(card);
    }

    @Override
    public void deleteCard(Long cardId) {
        cardRepository.deleteById(cardId);
    }

    @Override
    public List<CardDTO> getAllCards() {
        List<Card> cards = cardRepository.findAll();

        List<CardDTO> result = new ArrayList<>();
        for(Card card : cards) {
            CardDTO cardDTO = cardMapper.toCardDTO(card);
            if(cardDTO.getNumber() != null) {
                String decryptedNumber = encryptionUtil.decrypt(cardDTO.getNumber());
                cardDTO.setNumber(decryptedNumber);
            }
            result.add(cardDTO);
        }
        return result;
    }

    @Override
    @Transactional
    public void transferMoney(Long fromCardId, Long toCardId, BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("Amount must be greater than zero");
        }

        Card fromCard = cardRepository.findById(fromCardId).orElseThrow(() -> new RuntimeException("Card not found"));
        Card toCard = cardRepository.findById(toCardId).orElseThrow(() -> new RuntimeException("Card not found"));

        if(fromCard.getStatus().equals(Status.ACTIVE) && toCard.getStatus().equals(Status.ACTIVE)){
            if(fromCard.getBalance().compareTo(amount) >= 0){
                fromCard.setBalance(fromCard.getBalance().subtract(amount));
                toCard.setBalance(toCard.getBalance().add(amount));
                cardRepository.save(fromCard);
                cardRepository.save(toCard);
            }
            else {
                throw new RuntimeException("Insufficient funds");
            }
        }
        else {
            throw new RuntimeException("One of cards are not active");
        }
    }

    @Override
    public Page<CardDTO> getAllCardsByUser(Long userId, int page, int size, String number){
        Pageable pageable = PageRequest.of(page, size);
        Page<Card> cards;
        if(number != null && !number.isEmpty()){
            cards = cardRepository.findByOwnerIdAndNumberContaining(userId, number, pageable);
        }
        else {
            cards = cardRepository.findByOwnerId(userId, pageable);
        }
        return cards.map(cardMapper::toCardDTO);
    }

    @Override
    public BigDecimal showBalance(String cardNumber){
        String encryptedNumber = encryptionUtil.encrypt(cardNumber);
        Optional<Card> card = cardRepository.findByNumber(encryptedNumber);
        if(card.isPresent()){
            return card.get().getBalance();
        }
        else {
            throw new RuntimeException("Card not found");
        }
    }

    @Override
    public CardDTO changeCardStatus(String cardNumber, String status){
        String encryptedNumber = encryptionUtil.encrypt(cardNumber);
        Card card = cardRepository.findByNumber(encryptedNumber).orElseThrow(() -> new RuntimeException("Card not found"));
        card.setStatus(Status.valueOf(status));
        cardRepository.save(card);
        return cardMapper.toCardDTO(card);
    }

    @Override
    public BlockRequestDTO cardBlockRequest(String cardNumber, String reason){
        String encryptedNumber = encryptionUtil.encrypt(cardNumber);
        Card card = cardRepository.findByNumber(encryptedNumber).orElseThrow(() -> new RuntimeException("Card not found"));
        BlockRequest request = new BlockRequest();
        request.setReason(reason);
        request.setCardNumber(card.getNumber());
        blockRequestRepository.save(request);
        return blockRequestMapper.toDTO(request);
    }
}

