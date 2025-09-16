package com.example.bankcards.service;

import com.example.bankcards.dto.CardDTO;

public interface CardService {
    CardDTO createCard(CardDTO cardDTO);
    CardDTO updateCard(CardDTO cardDTO);
    CardDTO getCard(String cardId);
    void deleteCard(String cardId);
}
