package com.daniel.cardgame.cheat.players;

import com.daniel.cardgame.cheat.domain.Card;
import com.daniel.cardgame.cheat.domain.CheatGame;
import com.daniel.cardgame.cheat.domain.Play;
import com.daniel.cardgame.cheat.domain.enums.CardNumber;

import java.util.Optional;
import java.util.Scanner;

public class HumanPlayer extends Player {
    private static Scanner scanner = new Scanner(System.in);

    public HumanPlayer() {
        super(HumanPlayer.askName());
    }

    private static String askName() {
        System.out.println("Whats your name?");
        return scanner.nextLine();
    }

    @Override
    public Play makePlay(CheatGame cheatGame) {
        System.out.println(this.getHand());
        System.out.println("You can play "+cheatGame.possibleNextPlays());
        Optional<Card> selectedCard = Optional.empty();
        while (!selectedCard.isPresent()) {
            System.out.println("What card do you want to play");
            selectedCard = Optional.of(scanner)
                    .map(Scanner::nextInt)
                    .filter(cardIndex -> cardIndex < this.getHand().getCards().size())
                    .filter(cardIndex -> cardIndex >= 0)
                    .map(selectedCardIndex -> this.getHand().getCards().get(selectedCardIndex));
            if (!selectedCard.isPresent()) System.out.println("Invalid card");
        }
        Optional<CardNumber> numberToSay = Optional.empty();
        while (!numberToSay.isPresent()) {
            System.out.print("What number would you say?");
            String sayNumber = scanner.next();
            numberToSay = CardNumber.getByRepresentation(sayNumber).filter(toldNumber -> cheatGame.possibleNextPlays().contains(toldNumber));
            if (!numberToSay.isPresent()) System.out.println("Invalid number");
        }
        return cheatGame.makePlay(this, selectedCard.get(), numberToSay.get());
    }

    @Override
    public boolean willCallCheat(CheatGame cheatGame, Player currentPlayer) {
        if (!this.equals(currentPlayer)) {
            System.out.print("Would you like to call cheat? Y/N");
            return scanner.next().equals("Y");
        }
        return false;
    }
}
