package com.ludo.Ludo.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SignInRequest {
    private  String username;
    private  String password;
}
