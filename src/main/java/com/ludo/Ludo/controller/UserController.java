package com.ludo.Ludo.controller;

import com.ludo.Ludo.payload.CreateGuestResponse;
import com.ludo.Ludo.payload.SignUpRequest;
import com.ludo.Ludo.payload.SignUpResponse;
import com.ludo.Ludo.service.UserDetailsManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserDetailsManagerService userDetailsManagerService;

    @GetMapping("/api/createguest")
    public CreateGuestResponse createGuest(){

        try{
            return this.userDetailsManagerService.createGuestUser();
        }
        catch (Exception ex){
            throw ex;
        }
    }

    @RequestMapping("/api/signup")
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
}
