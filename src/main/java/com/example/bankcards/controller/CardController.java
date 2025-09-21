package com.example.bankcards.controller;


import com.example.bankcards.dto.BlockRequestDTO;
import com.example.bankcards.dto.CardDTO;
import com.example.bankcards.dto.TransferRequest;
import com.example.bankcards.service.CardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
@Tag(name = "Карты", description = "API для работы с картами")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<CardDTO> createCard(@RequestBody CardDTO cardDTO) {
        return ResponseEntity.ok(cardService.createCard(cardDTO));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public ResponseEntity<CardDTO> updateCard(@RequestBody CardDTO cardDTO) {
        return ResponseEntity.ok(cardService.updateCard(cardDTO));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<CardDTO> getCard(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.getCardById(id));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/all")
    public ResponseEntity<List<CardDTO>> getAllCards() {
        return ResponseEntity.ok(cardService.getAllCards());
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<CardDTO> deleteCard(@RequestParam Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }

//    @PreAuthorize("hasRole('USER')")
    @PostMapping("/transfer")
    public ResponseEntity<String> transferCard(@RequestBody TransferRequest transferRequest) {
        cardService.transferMoney(transferRequest.getFromCardId(), transferRequest.getToCardId(), transferRequest.getAmount());
        return ResponseEntity.ok("Transfer successful");
    }

//    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get/balance")
    public ResponseEntity<BigDecimal> getBalance(@RequestParam String cardNumber) {
        return ResponseEntity.ok(cardService.showBalance(cardNumber));
    }

//    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get/my")
    public ResponseEntity<Page<CardDTO>> getMyCards(@RequestParam Long userId,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size,
                                                    @RequestParam(required = false) String number) {
        return ResponseEntity.ok(cardService.getAllCardsByUser(userId, page, size, number));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/change/status")
    public ResponseEntity<CardDTO> changeStatus(@RequestParam String number, @RequestParam String status) {
        return ResponseEntity.ok(cardService.changeCardStatus(number, status));
    }

//    @PreAuthorize("hasRole('USER')")
    @PostMapping("/new/block_request")
    public ResponseEntity<BlockRequestDTO> newBlockRequest(@RequestParam String number, @RequestParam String reason) {
        return ResponseEntity.ok(cardService.cardBlockRequest(number, reason));
    }
}
