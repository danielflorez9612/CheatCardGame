package com.daniel.cardgame.cheat.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Objects;

@Data
@RequiredArgsConstructor
public class Player {
    @NonNull
    private String name;
    private Hand hand = new Hand();
    private boolean out;

    public void giveCards(Collection<Card> pile) {
        this.hand.addCards(pile);
    }

    public void giveCard(Card card) {
        this.hand.addCard(card);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !getClass().equals(o.getClass())) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", hand=" + hand.getCards() +
                ", out=" + out +
                '}';
    }
}
