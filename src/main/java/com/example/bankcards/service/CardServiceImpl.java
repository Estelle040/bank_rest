package com.example.bankcards.service;

import com.example.bankcards.dto.CardDTO;
import com.example.bankcards.dto.UserDTO;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.Status;
import com.example.bankcards.entity.User;
import com.example.bankcards.repository.CardRepository;
import com.example.bankcards.util.CardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public CardServiceImpl(CardRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }

    @Override
    public CardDTO createCard(CardDTO cardDTO) {
        Card card = cardMapper.toCard(cardDTO);
        card = cardRepository.save(card);
        return cardMapper.toCardDTO(card);
    }

    @Override
    public CardDTO updateCard(CardDTO cardDTO) {
//        Card card = cardMapper.toCard(cardDTO);
//        card.setStatus(cardDTO.getStatus());
        return null;
    }

    @Override
    public CardDTO getCard(String cardId) {
        // реализуй логику получения
        return null;
    }

    @Override
    public void deleteCard(String cardId) {
        // реализуй логику удаления
    }
}

