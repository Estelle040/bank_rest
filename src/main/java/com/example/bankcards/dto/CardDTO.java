package com.example.bankcards.dto;

import com.example.bankcards.entity.Status;
import com.example.bankcards.entity.User;
import jakarta.persistence.*;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CardDTO {
    private Long id;
    private String number;
    private UserDTO owner;
    private LocalDate expiryDate;
    private String status;
    private BigDecimal balance;
}
