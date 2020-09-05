package com.daniel.cardgame.cheat;

import com.daniel.cardgame.cheat.domain.*;
import com.daniel.cardgame.cheat.players.HumanPlayer;
import com.daniel.cardgame.cheat.players.ia.RandomIA;
import com.daniel.cardgame.cheat.players.Player;

import java.util.*;

public class Cheat {
    public static void main(String[] args) {
        List<Player> players = Arrays.asList(
                new HumanPlayer(),
                new RandomIA("juan"),
                new RandomIA("camilo"),
                new RandomIA("laura")
        );
        Deck standardDeck = DeckFactory.standardDeck();
        CheatGame cheatGame = new CheatGame(players, standardDeck);
        int turn = 0;
        Player nextPlayer;
        do {
            nextPlayer = cheatGame.getNextPlayer(turn++);
            int startingSize = nextPlayer.getHand().getCards().size();
            System.out.println("Its " + nextPlayer.getName() + "'s turn! he has " + startingSize + " cards");
            Play play = nextPlayer.makePlay(cheatGame);
            System.out.println("He played a "+ play.getNumberTold().getRepresentation());
            Player finalNextPlayer = nextPlayer;
            Optional<Player> someoneWhoCalledCheat = players.stream().filter(player -> player.willCallCheat(cheatGame, finalNextPlayer)).findFirst();
            if (someoneWhoCalledCheat.isPresent()) {
                Player whoCalledCheat = someoneWhoCalledCheat.get();
                System.out.println(whoCalledCheat.getName() + " called cheat");
                Optional.of(play)
                        .map(lastPlayed -> lastPlayed.getPlayer().getName() + " said he played " + lastPlayed.getNumberTold().getRepresentation() + " and he actually played " + lastPlayed.getCardPlayed())
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
