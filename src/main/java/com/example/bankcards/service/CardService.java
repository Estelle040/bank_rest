package com.example.bankcards.service;

import com.example.bankcards.dto.CardDTO;

import java.util.List;

public interface CardService {
    CardDTO createCard(CardDTO cardDTO);
    CardDTO updateCard(CardDTO cardDTO);
    CardDTO getCardById(Long cardId);
    void deleteCard(Long cardId);
    List<CardDTO> getAllCards();
}
