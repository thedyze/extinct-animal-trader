package com.company;

public class Game {
    public static int currentTurn = 0;

    public static void turn() {
            if (currentTurn < MainMenu.totalTurns) {
                currentTurn++;
            }
            for (MainMenu.player = 1; MainMenu.player <= MainMenu.numberOfPlayers; MainMenu.player++) {
                System.out.println("Turn:" +currentTurn + ", Player: " + MainMenu.pNames[MainMenu.player-1]);
            }
    }
}
