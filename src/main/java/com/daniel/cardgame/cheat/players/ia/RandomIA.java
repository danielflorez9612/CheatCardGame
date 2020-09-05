package com.daniel.cardgame.cheat.players.ia;

import com.daniel.cardgame.cheat.domain.Card;
import com.daniel.cardgame.cheat.domain.CheatGame;
import com.daniel.cardgame.cheat.domain.Play;
import com.daniel.cardgame.cheat.domain.enums.CardNumber;
import com.daniel.cardgame.cheat.players.Player;

import java.util.Objects;
import java.util.Random;

public class RandomIA extends Player {

    Random random;

    public RandomIA(String name) {
        super(name);
        this.random = new Random();
    }

    @Override
    public Play makePlay(Player player, CheatGame cheatGame) {
        int handSize = player.getHand().getCards().size();
        Card cardToPlay = player.getHand().getCards().get(random.nextInt(handSize));
        CardNumber numberToSay = cheatGame.possibleNextPlays().get(random.nextInt(cheatGame.possibleNextPlays().size()));
        return cheatGame.makePlay(player, cardToPlay, numberToSay);
    }

    @Override
    public boolean willCallCheat(CheatGame cheatGame, Player currentPlayer) {
        return !Objects.equals(this, currentPlayer) && random.nextInt(10) >= 6;
    }

}
