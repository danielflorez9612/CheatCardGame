package com.daniel.cardgame.cheat.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Getter
public enum CardNumber {
    ACE("A",1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4",4),
    FIVE("5",5),
    SIX("6",6),
    SEVEN("7",7),
    EIGHT("8",8),
    NINE("9",9),
    TEN("10",10),
    JACK("J",11),
    QUEEN("Q",12),
    KING("K",13)
    ;

    private String representation;
    private Integer number;

    public static Optional<CardNumber> getByRepresentation(String representation) {
        return Arrays.stream(CardNumber.values()).filter(n -> Objects.equals(n.getRepresentation(), representation)).findFirst();
    }

    public static Optional<CardNumber> getByValue(int number) {
        return Arrays.stream(CardNumber.values()).filter(n -> Objects.equals(n.getNumber(), number)).findFirst();
    }
}
