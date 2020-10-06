package com.company;
import java.util.*;

public class Game {
    public static int currentTurn = 0;


    public static void turn() {
            if (currentTurn < MainMenu.totalTurns) {
                currentTurn++;
            }

            for (MainMenu.player = 1; MainMenu.player <= MainMenu.numberOfPlayers; MainMenu.player++) {
                System.out.println("Turn:" +currentTurn + ", Player: " + MainMenu.pNames.get(MainMenu.player-1));
                System.out.println("You have " + MainMenu.playerCash.get(MainMenu.player-1) + "€");
            }
    }
}
