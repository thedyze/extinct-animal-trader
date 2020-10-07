package com.company;

import java.util.Scanner;

public class Game {
    public static int currentTurn = 0;
    public static int totalTurns;
    public static int numberOfPlayers;
    public static int player = 1;
    public static Scanner scanner = new Scanner(System.in);

    public static void turn() {
            if (currentTurn <totalTurns) {
                currentTurn++;
            }

            for (player = 1; player <= numberOfPlayers; player++) {
                System.out.println("Turn: " +currentTurn + "/" +totalTurns +
                        ". Player " + Player.names.get(player -1) + ", you have " + Player.cash + "€");
                playerAction();
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
    public static void init() {

        Dialogs.clear();
        System.out.println("      Welcome to: \n \u001B[1mEXTINCT ANIMAL TRADER\033[0;0m \n ----------------------\n");
        var input = Dialogs.promptInt("Press 1 for new game :>", 1, 1);
        Game.numberOfPlayers = Dialogs.promptInt("How may players? (1-4)", 1, 4);

        //loop through number of players and set names, add starting cash
        for (int i = 0; i < Game.numberOfPlayers; i++ ) {
            System.out.println("Player "  + (i + 1) + " name?");
            Player.names.add (scanner.nextLine());
            Player.cash = 10000;
        }
        //select number of game turns
        Game.totalTurns = Dialogs.promptInt("Select Game duration? (5-30 turns):", 5,30);
        /*
        System.out.println("Welcome to the game ");
        for (String pName : Game.pNames) {
            System.out.println(pName);
        }
         */


    }
}

