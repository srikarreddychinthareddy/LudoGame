package com.ludo.Ludo.adaptor.payloadToModel;

import com.ludo.Ludo.constants.RoleType;
import com.ludo.Ludo.models.Player;
import com.ludo.Ludo.payload.SignUpRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public  class PlayerAdapter {
    public static Player adapter(SignUpRequest signUpRequest){
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(RoleType.User.toString());
        Player player = new Player(signUpRequest.getUsername(), signUpRequest.getPassword(),simpleGrantedAuthority);
        return player;
    }
}
