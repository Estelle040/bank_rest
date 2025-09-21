package com.example.bankcards.util;

import com.example.bankcards.dto.BlockRequestDTO;
import com.example.bankcards.entity.BlockRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlockRequestMapper {
    BlockRequestDTO toDTO(BlockRequest blockRequest);
    BlockRequest toEntity(BlockRequestDTO blockRequestDTO);
}
