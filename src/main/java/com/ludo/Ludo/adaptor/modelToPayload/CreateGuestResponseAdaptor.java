package com.ludo.Ludo.adaptor.modelToPayload;

import com.ludo.Ludo.models.Player;
import com.ludo.Ludo.payload.CreateGuestResponse;

public class CreateGuestResponseAdaptor {


    public CreateGuestResponse adapt(Player player) {
        return CreateGuestResponse.builder()
                .guestId(player.getId())
                .guestName(player.getUsername())
                .build();
    }
}
