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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int handSize = cards.size();
        builder.append("Hand={ cards=[");
        for (int i = 0; i < handSize; i++) {
            if (i>0) builder.append(",");
            builder.append(" [");
            builder.append(i);
            builder.append("]");
            builder.append(cards.get(i));
        }
        builder.append("]");
        return builder.toString();
    }
}
