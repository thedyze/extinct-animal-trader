package com.company;
import java.util.*;

public class Game {
    public static int currentTurn = 0;
    public static int totalTurns = 5;
    public static int numberOfPlayers = 4;
    public static int player = 1;
    public static ArrayList<String> pNames = new ArrayList<>();
    public static ArrayList<Integer> pCash = new ArrayList<>();


    public static void turn() {
            if (currentTurn <totalTurns) {
                currentTurn++;
            }

            for (player = 1; player <= numberOfPlayers; player++) {
                System.out.println("Turn: " +currentTurn + "/" +totalTurns +", Player: " + pNames.get(player-1));
                System.out.println("You have " + pCash.get(player-1) + "€");
                playerAction();
            }
    }

    public static void playerAction() {
        var input = Dialogs.promptInt("Choose your action:\n 1.Visit Store 2.Feed Animals 3.Mate Animals"
                , 1, 3);
        /*
        switch (input) {
            case 1: Store.menu();
            case 2: feedAnimals();
            case 3: mateAnimals();
        }
         */
    }
}

