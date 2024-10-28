package com.ludo.Ludo.payload;

import lombok.Builder;

@Builder
public class SignUpResponse {
    Boolean isSuccess;
    String errorMessage;
}
