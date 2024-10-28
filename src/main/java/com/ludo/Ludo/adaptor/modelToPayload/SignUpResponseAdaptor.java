package com.ludo.Ludo.adaptor.modelToPayload;

import com.ludo.Ludo.payload.SignUpResponse;
import org.springframework.stereotype.Component;

@Component
public class SignUpResponseAdaptor {
    public SignUpResponse adaptor(boolean isSuccess,String errorMeassage){
        return SignUpResponse.builder()
                .isSuccess(isSuccess)
                .errorMessage(errorMeassage)
                .build();
    }
}
