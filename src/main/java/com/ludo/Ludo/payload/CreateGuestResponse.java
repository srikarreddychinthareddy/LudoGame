package com.ludo.Ludo.payload;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateGuestResponse {
   public String guestId;
   public String guestName;
}
