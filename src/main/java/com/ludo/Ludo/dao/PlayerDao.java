package com.ludo.Ludo.dao;

import com.ludo.Ludo.models.Player;

public interface PlayerDao {
    public Player loadPlayerByUsername(String username);
    public void save(Player player);
}
