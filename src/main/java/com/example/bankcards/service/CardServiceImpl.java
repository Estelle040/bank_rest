package com.example.bankcards.service;

import com.example.bankcards.dto.CardDTO;
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
        return cardMapper.toCardDTO(cardRepository.save(cardMapper.toCard(cardDTO)));
    }

    @Override
    public CardDTO updateCard(CardDTO cardDTO) {
        // реализуй логику обновления
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

