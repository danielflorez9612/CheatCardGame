package com.daniel.cardgame.cheat.domain;

import lombok.Data;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Data
public class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new LinkedList<>();
    }

    public void addCards(Collection<Card> pile) {
        this.cards.addAll(pile);
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }
}
