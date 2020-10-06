package com.company;
import java.util.*;

public class MainMenu {

    public static int player = 1;


    public static void init() {

        Dialogs.clear();
        System.out.println("      Welcome to: \n \u001B[1mEXTINCT ANIMAL TRADER\033[0;0m \n ----------------------\n");
        var input = Dialogs.promptInt("Press 1 for new game :>", 1, 1);
        var numberOfPlayers = Dialogs.promptInt("How may players? (1-4)", 1, 4);
        String [] pNames = new String [numberOfPlayers];

        //loop through number of players and set names.
        for (player = 1; player <= numberOfPlayers; player++ ) {
            pNames[player-1] = Dialogs.prompt("What's your name player " + player + "?");

        }
        System.out.println("Welcome to the game ");
        for (player = 0; player < numberOfPlayers; player++ ) {
            System.out.println(pNames[player]);
        }
    }

}
