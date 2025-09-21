package com.example.bankcards.util;

import com.example.bankcards.dto.BlockRequestDTO;
import com.example.bankcards.entity.BlockRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-21T20:13:38+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class BlockRequestMapperImpl implements BlockRequestMapper {

    @Override
    public BlockRequestDTO toDTO(BlockRequest blockRequest) {
        if ( blockRequest == null ) {
            return null;
        }

        BlockRequestDTO blockRequestDTO = new BlockRequestDTO();

        blockRequestDTO.setReason( blockRequest.getReason() );

        return blockRequestDTO;
    }

    @Override
    public BlockRequest toEntity(BlockRequestDTO blockRequestDTO) {
        if ( blockRequestDTO == null ) {
            return null;
        }

        BlockRequest blockRequest = new BlockRequest();

        blockRequest.setReason( blockRequestDTO.getReason() );

        return blockRequest;
    }
}
