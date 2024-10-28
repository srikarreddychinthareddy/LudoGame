package com.ludo.Ludo.service;

import com.ludo.Ludo.dao.PlayerDao;
import com.ludo.Ludo.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    PlayerDao playerDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return playerDao.loadPlayerByUsername(username);
    }
}
