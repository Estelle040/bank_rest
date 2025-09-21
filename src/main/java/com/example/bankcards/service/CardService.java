package com.example.bankcards.service;


import com.example.bankcards.dto.BlockRequestDTO;
import com.example.bankcards.dto.CardDTO;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface CardService {
    CardDTO createCard(CardDTO cardDTO);
    CardDTO updateCard(CardDTO cardDTO);
    CardDTO getCardById(Long cardId);
    void deleteCard(Long cardId);
    List<CardDTO> getAllCards();

    void transferMoney(Long fromCardId, Long toCardId, BigDecimal amount);

    Page<CardDTO> getAllCardsByUser(Long userId, int page, int size, String number);

    BigDecimal showBalance(String cardNumber);

    CardDTO changeCardStatus(String cardNumber, String status);


    BlockRequestDTO cardBlockRequest(String cardNumber, String reason);
}
