package com.daniel.cardgame.cheat.domain;

import com.daniel.cardgame.cheat.domain.enums.CardNumber;
import lombok.Getter;

import java.util.*;

public class CheatGame {
    private List<Player> players;
    private Deque<Play> playQueue;
    private Deque<Card> pile;
    @Getter
    private CardNumber currentCardValue;

    public CheatGame(List<Player> players, Deck deck) {
        this.players = players;
        currentCardValue = CardNumber.ACE;
        playQueue = new ArrayDeque<>();
        pile = new ArrayDeque<>();
        Collections.shuffle(deck.getCards());
        for (int i = 0; i < deck.getCards().size(); i++) {
            getNextPlayer(i).giveCard(deck.getCards().get(i));
        }
    }

    public List<CardNumber> possibleNextPlays() {
        List<CardNumber> nextPlays = new LinkedList<>();
        nextPlays.add(currentCardValue);
        nextPlays.add(CardNumber.ACE);
        if (!Objects.equals(CardNumber.KING, currentCardValue)) {
            nextPlays.addAll(Arrays.asList(CardNumber.values()).subList(currentCardValue.ordinal() + 1, CardNumber.values().length));
        }
        return nextPlays;
    }

    public Player getNextPlayer(int turn) {
        int playerPos = turn % players.size();
        return players.get(playerPos);
    }

    public Play makePlay(Player player, Card card, CardNumber numberTold) {
        Play play = new Play(player, card, numberTold);
        playQueue.offerLast(play);
        player.getHand().getCards().remove(card);
        pile.offerFirst(card);
        currentCardValue = numberTold;
        return play;
    }

    private void reset() {
        pile = new ArrayDeque<>();
    }

    public Optional<Player> callCheat(Player whoCalledCheat) {
        Optional<Play> lastPlay = Optional.ofNullable(playQueue.peekLast());
        if (lastPlay.filter(Play::isCheat).isPresent()) {
            for (Player player : players) {
                if (Objects.equals(player, lastPlay.get().getPlayer())) {
                    player.giveCards(pile);
                    reset();
                    return Optional.of(player);
                }
            }
        }
        return Optional.of(whoCalledCheat);
    }

    @Override
    public String toString() {
        return "CheatGame{" +
                "players" + players +
                ", playQueue=" + playQueue +
                ", pile=" + pile +
                '}';
    }
}
