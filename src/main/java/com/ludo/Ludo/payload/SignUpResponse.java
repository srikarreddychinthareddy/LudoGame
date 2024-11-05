package com.ludo.Ludo.payload;

import lombok.Builder;

@Builder
public class SignUpResponse {
    public Boolean isSuccess;
    public String errorMessage;
}
