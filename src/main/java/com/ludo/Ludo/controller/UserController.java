package com.ludo.Ludo.controller;

import com.ludo.Ludo.adaptor.modelToPayload.CreateGuestResponseAdaptor;
import com.ludo.Ludo.adaptor.modelToPayload.SignUpResponseAdaptor;
import com.ludo.Ludo.models.Player;
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

    @Autowired
    SignUpResponseAdaptor signUpResponseAdaptor;

    @Autowired
    CreateGuestResponseAdaptor createGuestResponseAdaptor;

    @GetMapping("/api/createguest")
    public CreateGuestResponse createGuest(){

        try{
            Player guest = userDetailsManagerService.createGuestUser();
            return createGuestResponseAdaptor.adapt(guest);
        }
        catch (Exception ex){
            throw ex;
        }
    }

    @RequestMapping("/api/signup")
    public SignUpResponse createUser(@RequestBody SignUpRequest signUpRequest){
        try{
            userDetailsManagerService.createUser(signUpRequest);
            return signUpResponseAdaptor.adaptor(true,null);
        }
        catch (Exception ex){
            return signUpResponseAdaptor.adaptor(false,ex.getMessage());
        }
    }
}
