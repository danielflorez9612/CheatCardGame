package com.daniel.cardgame.cheat.domain;

import com.daniel.cardgame.cheat.domain.enums.Suit;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeckFactory {
    public static Deck standardDeck() {
        List<Card> deque = new LinkedList<>();
        deque.addAll(createSuit(13, Suit.HEARTS));
        deque.addAll(createSuit(13, Suit.CLOVERS));
        deque.addAll(createSuit(13, Suit.DIAMONDS));
        deque.addAll(createSuit(13, Suit.PIKES));
        return new Deck(deque, 13);
    }

    private static Collection<Card> createSuit(int numberOfCards, Suit suit) {
        Collection<Card> cards = new LinkedList<>();
        for(int i = 0; i<= numberOfCards; i++) {
            cards.add(new Card(i, suit));
        }
        return cards;
    }
}
