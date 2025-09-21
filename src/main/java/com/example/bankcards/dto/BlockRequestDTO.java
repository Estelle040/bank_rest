package com.example.bankcards.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlockRequestDTO {
    private String number;
    private String reason;
}