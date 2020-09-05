package com.daniel.cardgame.cheat.players;

import com.daniel.cardgame.cheat.domain.Card;
import com.daniel.cardgame.cheat.domain.CheatGame;
import com.daniel.cardgame.cheat.domain.Hand;
import com.daniel.cardgame.cheat.domain.Play;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Objects;

@Data
@RequiredArgsConstructor
public abstract class Player {
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
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    public abstract Play makePlay(CheatGame cheatGame);

    public abstract boolean willCallCheat(CheatGame cheatGame, Player currentPlayer);

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
