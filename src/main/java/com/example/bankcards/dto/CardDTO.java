package com.example.bankcards.dto;

import com.example.bankcards.entity.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CardDTO {
    @Schema(description = "ID карты", example = "1")
    private Long id;

    @Schema(description = "Номер карты", example = "4111 1111 1111 1111")
    private String number;

    @Schema(description = "Владелец карты")
    private UserDTO owner;

    @Schema(description = "Срок действия", example = "2027-12-01")
    private LocalDate expiryDate;

    @Schema(description = "Статус карты", example = "ACTIVE")
    private Status status;

    @Schema(description = "Баланс", example = "1000.50")
    private BigDecimal balance;
}
