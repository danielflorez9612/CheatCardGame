package com.daniel.cardgame.cheat;

import com.daniel.cardgame.cheat.domain.*;
import com.daniel.cardgame.cheat.domain.enums.CardNumber;

import java.util.*;

public class Cheat {
    public static void main(String[] args) {
        List<Player> players = Arrays.asList(
                new Player("pepe"),
                new Player("juan"),
                new Player("camilo"),
                new Player("laura")
        );
        Deck standardDeck = DeckFactory.standardDeck();
        CheatGame cheatGame = new CheatGame(players, standardDeck);
        int turn = 0;
        Player nextPlayer;
        int playerCount = players.size();
        do {
            nextPlayer = cheatGame.getNextPlayer(turn++);
            int startingSize = nextPlayer.getHand().getCards().size();
            System.out.println("Its " + nextPlayer.getName() + "'s turn! he has " + startingSize + " cards");
            Random random = new Random();
            int handSize = nextPlayer.getHand().getCards().size();
            Card cardToPlay = nextPlayer.getHand().getCards().get(random.nextInt(handSize));
            CardNumber numberToSay = cheatGame.possibleNextPlays().get(random.nextInt(cheatGame.possibleNextPlays().size()));
            Play play = cheatGame.makePlay(nextPlayer, cardToPlay, numberToSay);
            System.out.println("He played a "+ play.getNumberTold().getRepresentation());
            boolean callCheat = random.nextBoolean();
            Player whoCalledCheat = players.get(random.nextInt(playerCount));
            if (callCheat && !Objects.equals(whoCalledCheat, nextPlayer)) {
                System.out.println(whoCalledCheat.getName() + " called cheat");
                Optional.of(play)
                        .map(lastPlayed -> lastPlayed.getPlayer().getName() + " said he played " + lastPlayed.getNumberTold() + " and he actually played " + lastPlayed.getCardPlayed())
                        .ifPresent(System.out::println);
                Optional<Player> loser = cheatGame.callCheat(whoCalledCheat);
                loser.ifPresent(player -> System.out.println(player.getName() + " lose! now he has " + player.getHand().getCards().size() + " cards"));
            } else {
                System.out.println("Now " + nextPlayer.getName() + " has " + nextPlayer.getHand().getCards().size() + " cards");
            }
            System.out.println();
        } while (!nextPlayer.getHand().getCards().isEmpty());
        System.out.println(nextPlayer.getName() + " WON!");
    }
}
