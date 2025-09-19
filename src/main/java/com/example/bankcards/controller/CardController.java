package com.example.bankcards.controller;


import com.example.bankcards.dto.CardDTO;
import com.example.bankcards.dto.TransferRequest;
import com.example.bankcards.service.CardService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/new")
    public ResponseEntity<CardDTO> createCard(@RequestBody CardDTO cardDTO) {
        return ResponseEntity.ok(cardService.createCard(cardDTO));
    }

    @PostMapping("/update")
    public ResponseEntity<CardDTO> updateCard(@RequestBody CardDTO cardDTO) {
        return ResponseEntity.ok(cardService.updateCard(cardDTO));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CardDTO> getCard(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.getCardById(id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<CardDTO>> getAllCards() {
        return ResponseEntity.ok(cardService.getAllCards());
    }

    @DeleteMapping
    public ResponseEntity<CardDTO> deleteCard(@RequestParam Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferCard(@RequestBody TransferRequest transferRequest) {
        cardService.transferMoney(transferRequest.getFromCardId(), transferRequest.getToCardId(), transferRequest.getAmount());
        return ResponseEntity.ok("Transfer successful");
    }

    @GetMapping("/get/balance")
    public ResponseEntity<BigDecimal> getBalance(@RequestParam String cardNumber) {
        return ResponseEntity.ok(cardService.showBalance(cardNumber));
    }

    @GetMapping("/get/my")
    public ResponseEntity<Page<CardDTO>> getMyCards(@RequestParam Long userId,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size,
                                                    @RequestParam(required = false) String number) {
        return ResponseEntity.ok(cardService.getAllCardsByUser(userId, page, size, number));
    }

    @PatchMapping("/change/status")
    public ResponseEntity<CardDTO> changeStatus(@RequestParam String number, @RequestParam String status) {
        return ResponseEntity.ok(cardService.changeCardStatus(number, status));
    }

//    @PutMapping("/new/block_request")
//    public ResponseEntity<BlockRequest> newBlockRequest(@RequestParam String number, @RequestParam String reason) {
//        return ResponseEntity.ok(cardService.cardBlockRequest(number, reason));
//    }
}
