package com.example.bankcards.service;

import com.example.bankcards.dto.CardDTO;
import com.example.bankcards.dto.UserDTO;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.Status;
import com.example.bankcards.entity.User;
import com.example.bankcards.repository.CardRepository;
import com.example.bankcards.repository.UserRepository;
import com.example.bankcards.util.CardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final UserRepository userRepository;

    public CardServiceImpl(CardRepository cardRepository, CardMapper cardMapper, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
        this.userRepository = userRepository;
    }

    @Override
    public CardDTO createCard(CardDTO cardDTO) {
        Card card = cardMapper.toCard(cardDTO);
        User user = userRepository.findById(cardDTO.getOwner().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        card.setOwner(user);
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
        return cardRepository.findAll().stream()
                .map(cardMapper::toCardDTO)
                .collect(Collectors.toList());
    }
}

