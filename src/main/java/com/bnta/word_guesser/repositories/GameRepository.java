package com.bnta.word_guesser.repositories;

import com.bnta.word_guesser.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByCompleteTrue();

}
