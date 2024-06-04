package com.bnta.word_guesser.repositories;

import com.bnta.word_guesser.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
