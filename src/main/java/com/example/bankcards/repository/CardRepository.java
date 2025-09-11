package com.example.bankcards.repository;

import com.example.bankcards.entity.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Long> {
}
