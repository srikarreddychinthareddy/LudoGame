package com.ludo.Ludo.repository;

import com.ludo.Ludo.models.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, String> {
    Player  findByUsername(String username);
}
