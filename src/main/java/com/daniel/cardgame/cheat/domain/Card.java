package com.daniel.cardgame.cheat.domain;

import com.daniel.cardgame.cheat.domain.enums.CardNumber;
import com.daniel.cardgame.cheat.domain.enums.Suit;
import lombok.Data;


@Data
public class Card {
    private Suit suit;
    private CardNumber number;

    public Card(int number, Suit suit) {
        this.suit = suit;
        this.number = CardNumber.getByValue(number).orElseThrow(() -> new IllegalArgumentException("Invalid Card"));
    }

    @Override
    public String toString() {
        return "{"+number.getRepresentation()+","+suit+"}";
    }
}
