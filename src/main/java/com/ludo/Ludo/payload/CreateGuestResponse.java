package com.ludo.Ludo.payload;

import lombok.Builder;

@Builder
public class CreateGuestResponse {
    String guestId;
    String guestName;
}
