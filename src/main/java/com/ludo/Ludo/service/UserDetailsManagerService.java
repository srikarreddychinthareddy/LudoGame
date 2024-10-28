package com.ludo.Ludo.service;

import com.ludo.Ludo.adaptor.payloadToModel.PlayerAdapter;
import com.ludo.Ludo.dao.PlayerDao;
import com.ludo.Ludo.models.Player;
import com.ludo.Ludo.payload.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserDetailsManagerService {

    @Autowired
    PlayerDao playerDao;
    @Autowired
    RedisTemplate<String, Player> redisTemplate;

    public Player createGuestUser() {
        String guestId;
        do{
            guestId = UUID.randomUUID().toString();
        }while(redisTemplate.hasKey(guestId));

        String username = "Guest_"+guestId;
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("role");
        Player player = new Player(username, null, simpleGrantedAuthority);
        player.setId(guestId);
        this.saveToSecurityContext(player);
        return player;
    }

    public void createUser(SignUpRequest signUpRequest){
        Player player = PlayerAdapter.adapter(signUpRequest);
        playerDao.save(player);
        this.saveToSecurityContext(player);
    }

    public void saveToSecurityContext(Player player){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                UsernamePasswordAuthenticationToken(player.getUsername(),player.getPassword(),player.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
}
