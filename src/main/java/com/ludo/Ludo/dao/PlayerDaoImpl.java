package com.ludo.Ludo.dao;

import com.ludo.Ludo.models.Player;
import com.ludo.Ludo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerDaoImpl implements PlayerDao{
    @Autowired
    PlayerRepository playerRepository;
    @Override
    public Player loadPlayerByUsername(String username) {
        return playerRepository.loadPlayerByUsername(username);
    }
    @Override
    public void save(Player player){
        playerRepository.save(player);
    }
}
