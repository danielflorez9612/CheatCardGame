package com.daniel.cardgame.cheat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Deck {
    protected List<Card> cards;
    private Integer cardsPerSuit;
}
