package com.ridango.game.spring.jpa.h2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ridango.game.spring.jpa.h2.model.GameData;

@Component
public interface GameDataRepository extends JpaRepository<GameData, Long> {
	
	@Query("SELECT gd.value FROM GameData gd WHERE gd.name = 'highscore'")
	//@Query("SELECT value FROM GameData gd where name = 'highscore'")
	//@Query("Select  from gamedata")
	Optional<Integer> getHighScore();
	
	@Query("UPDATE GameData gd SET value = ?1  WHERE gd.name = 'highscore'")
	@Transactional
	@Modifying
	void setHighScore(int highScore);
	

}
