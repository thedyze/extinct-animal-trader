package com.company;

public class Game {
    public static int currentTurn = 0;
    public static int totalTurns;
    public static int numberOfPlayers;
    public static int player = 1;

    public static void turn() {
            if (currentTurn <totalTurns) {
                currentTurn++;
            }

            for (player = 1; player <= numberOfPlayers; player++) {
                System.out.println("Turn: " +currentTurn + "/" +totalTurns +
                        ". Player: " + Player.names.get(player -1) + ". You have " + Player.cash.get(player -1) + "€.");
                playerAction();
            }
    }

    public static void playerAction() {
        var input = Dialogs.promptInt("\nChoose your action:\n 1.Visit Store 2.Feed Animals 3.Mate Animals"
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

