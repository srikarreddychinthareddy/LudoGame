package com.ludo.Ludo.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class SignUpRequest {
    String username;
    String password;
}
