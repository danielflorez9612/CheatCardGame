package com.daniel.cardgame.cheat.domain;

import com.daniel.cardgame.cheat.domain.enums.CardNumber;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;


@Data
@AllArgsConstructor
public class Play {
    private Player player;
    private Card cardPlayed;
    private CardNumber numberTold;

    public boolean isCheat() {
        return !Objects.equals(cardPlayed.getNumber(), numberTold);
    }

    @Override
    public String toString() {
        return "Play{" +
                "player=" + player.getName() +
                ", cardPlayed=" + cardPlayed +
                ", cardTold=" + numberTold.getRepresentation() +
                '}';
    }
}
