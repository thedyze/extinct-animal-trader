package com.company;
import java.util.*;

public class MainMenu {
    public static Scanner scanner = new Scanner(System.in);

    public static void init() {

        Dialogs.clear();
        System.out.println("      Welcome to: \n \u001B[1mEXTINCT ANIMAL TRADER\033[0;0m \n ----------------------\n");
        var input = Dialogs.promptInt("Press 1 for new game :>", 1, 1);
        Game.numberOfPlayers = Dialogs.promptInt("How may players? (1-4)", 1, 4);

        //loop through number of players and set names, add cash
        for (int i = 0; i < Game.numberOfPlayers; i++ ) {
            System.out.println("Player "  + (i + 1) + " name?");
            Player.names.add (scanner.nextLine());
            Player.cash.add(10000);
        }

        Game.totalTurns = Dialogs.promptInt("Select Game duration? (5-30 turns):", 5,30);
        /*
        System.out.println("Welcome to the game ");
        for (String pName : Game.pNames) {
            System.out.println(pName);
        }
         */
        //select number of game turns

    }

}
