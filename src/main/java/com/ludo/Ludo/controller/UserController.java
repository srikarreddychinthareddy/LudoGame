package com.ludo.Ludo.controller;

import com.ludo.Ludo.models.Player;
import com.ludo.Ludo.payload.CreateGuestResponse;
import com.ludo.Ludo.payload.SignInRequest;
import com.ludo.Ludo.payload.SignUpRequest;
import com.ludo.Ludo.payload.SignUpResponse;
import com.ludo.Ludo.service.JwtService;
import com.ludo.Ludo.service.UserDetailsManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
public class UserController {

    @Autowired
    RedisTemplate<String, Player> redisTemplate;

    @Autowired
    UserDetailsManagerService userDetailsManagerService;

    @Autowired
    JwtService jwtService;

    @GetMapping("/api/createguest")
    public CreateGuestResponse createGuest(){

        try{
            return this.userDetailsManagerService.createGuestUser();
        }
        catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/api/signup")
    public SignUpResponse createUser(@RequestBody SignUpRequest signUpRequest){
        try{
            return this.userDetailsManagerService.createUser(signUpRequest);
        }
        catch (Exception ex){
            return SignUpResponse.builder()
                    .isSuccess(false)
                    .errorMessage(ex.getMessage())
                    .build();
        }
    }

    @RequestMapping("/getRndmInt")
    public String  getRndmInt(){
        return UUID.randomUUID().toString();
    }

    @PostMapping("/login")
    public String login(@RequestBody SignInRequest signInRequest){
        String result = userDetailsManagerService.verify(signInRequest);
        return result;
    }

    @GetMapping("/check")
    public String check(){
        return "thumbs up";
    }
}
