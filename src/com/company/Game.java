package com.company;
import java.util.ArrayList;


public class Game {
    public static int currentTurn = 1;
    //public static int totalTurns;
    public static ArrayList<Player> players = new ArrayList<>();

    static void turn(int totalPlayers, int totalTurns) {
        //loop game turns
        for (currentTurn = 1; currentTurn <= totalTurns; currentTurn++) {
            //loop player turns
            Player currentPlayer;
            for (int i = 0; i < totalPlayers; i++) {
                Dialogs.clear();
                currentPlayer = players.get(i);
                System.out.println("Turn: " + currentTurn + "/" + totalTurns +
                        ". Player: " + currentPlayer.getName() + ", you have "
                        + currentPlayer.getCash() + "€.");
                Player.actionTaken = false;
                playerAction();
            }
        }

    }
    public static void playerAction() {
        var input = Dialogs.promptInt("\nChoose your action:\n [1:Visit Store] [2:Feed Animals] [3:Mate Animals]"
                , 1, 3);

        switch (input) {
            case 1: Store.menu();

            case 2: //feedAnimals();
            case 3: //mateAnimals();
        }

    }

    static void init() {
        int numberOfPlayers;
        int totalTurns;
        //welcome
        Dialogs.clear();
        System.out.println("      Welcome to: \n \u001B[1mEXTINCT ANIMAL TRADER\033[0;0m \n ----------------------\n");
        var input = Dialogs.promptInt("Press 1 for new game :>", 1, 1);
        numberOfPlayers = Dialogs.promptInt("How many players? (1-4)", 1, 4);

        //add players
        for (int i = 0; i < numberOfPlayers; i++ ) {
            Player player = new Player(Dialogs.prompt("Player "  + (i + 1) + " name?"),i);
            players.add(player);
        }

        //select number of game turns
        totalTurns = Dialogs.promptInt("Select Game duration? (5-30 turns):", 5,30);
        Game.turn(numberOfPlayers, totalTurns);
    }
}

