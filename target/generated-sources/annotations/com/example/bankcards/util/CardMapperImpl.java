package com.example.bankcards.util;

import com.example.bankcards.dto.CardDTO;
import com.example.bankcards.entity.Card;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-18T17:19:01+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class CardMapperImpl implements CardMapper {

    @Override
    public CardDTO toCardDTO(Card card) {
        if ( card == null ) {
            return null;
        }

        CardDTO cardDTO = new CardDTO();

        cardDTO.setOwner( toUserDTO( card.getOwner() ) );
        cardDTO.setId( card.getId() );
        cardDTO.setNumber( card.getNumber() );
        cardDTO.setExpiryDate( card.getExpiryDate() );
        cardDTO.setStatus( card.getStatus() );
        cardDTO.setBalance( card.getBalance() );

        return cardDTO;
    }

    @Override
    public Card toCard(CardDTO cardDTO) {
        if ( cardDTO == null ) {
            return null;
        }

        Card card = new Card();

        card.setOwner( toUser( cardDTO.getOwner() ) );
        card.setId( cardDTO.getId() );
        card.setNumber( cardDTO.getNumber() );
        card.setExpiryDate( cardDTO.getExpiryDate() );
        card.setStatus( cardDTO.getStatus() );
        card.setBalance( cardDTO.getBalance() );

        return card;
    }
}
