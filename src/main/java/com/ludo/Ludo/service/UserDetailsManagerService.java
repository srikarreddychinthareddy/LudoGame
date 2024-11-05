package com.ludo.Ludo.service;

import com.ludo.Ludo.constants.RoleType;
import com.ludo.Ludo.models.Player;
import com.ludo.Ludo.payload.CreateGuestResponse;
import com.ludo.Ludo.payload.SignInRequest;
import com.ludo.Ludo.payload.SignUpRequest;
import com.ludo.Ludo.payload.SignUpResponse;
import com.ludo.Ludo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserDetailsManagerService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisTemplate<String, Player> redisTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;


    public CreateGuestResponse createGuestUser() {
        String guestId;
        do{
            guestId = UUID.randomUUID().toString();
        }while(redisTemplate.hasKey(guestId));

        String username = "Guest_"+guestId;
        Player player = Player.builder()
                .id(guestId)
                .role(RoleType.Guest.toString())
                .username(username)
                .password("")
                .build();
        redisTemplate.opsForValue().set(guestId,player);
        this.saveToSecurityContext(player);
        return CreateGuestResponse.builder()
                .guestName(username)
                .guestId(guestId)
                .build();
    }

    public SignUpResponse createUser(SignUpRequest signUpRequest){
        Player player = Player.builder()
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role("USER")
                .build();
        playerRepository.save(player);
        this.saveToSecurityContext(player);
        return SignUpResponse.builder()
                .isSuccess(true)
                .errorMessage("")
                .build();
    }

    public void saveToSecurityContext(Player player){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                UsernamePasswordAuthenticationToken(player.getUsername(),player.getPassword(),player.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    public String verify(SignInRequest signInRequest){
        Player player = Player.builder()
                .username(signInRequest.getUsername())
                .password(signInRequest.getPassword())
                .role("USER")
                .build();

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(player.getUsername(), player.getPassword(),player.getAuthorities()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(player.getUsername());
        }
        return "fail";
    }
}
