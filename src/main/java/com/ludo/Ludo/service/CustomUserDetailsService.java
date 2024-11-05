package com.ludo.Ludo.service;

import com.ludo.Ludo.models.Player;
import com.ludo.Ludo.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    PlayerRepository playerRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsManagerService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Inside CustomUserDetailsService: loadUserByUsername() called for username: {}", username);
        Player player = playerRepository.findByUsername(username);
        if (player != null) {
            logger.debug("User found: {}", player.getUsername());
        } else {
            logger.debug("User not found for username: {}", username);
        }
        return player;
    }
}
