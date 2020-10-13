package com.company;
import java.util.ArrayList;


public class Game {
    public static ArrayList<Player> players = new ArrayList<>();
    public static boolean actionsTaken;
    //loop game turns
    static void turn(int totalPlayers, int totalTurns) {
        int turn;
        for (turn = 1; turn <= totalTurns; turn++) {

            //loop player turns
            Player player;
            for (int i = 0; i < totalPlayers; i++) {
                Dialogs.clear();
                actionsTaken = false;
                player = players.get(i);
                System.out.println("Turn: " + turn + "/" + totalTurns +". Player: " + player.getName() + ", you have "
                        + player.getCash() + "€.");
                playerAction(player);
            }
        }

    }
    public static void playerAction(Player player) {
        var input = Dialogs.promptInt("\nChoose your action:\n [1:Visit Store] [2:Feed Animals] [3:Mate Animals]"
                , 1, 3);

        switch (input) {
            case 1: Store.storeFront(player);

            case 2: //feedAnimals();
            case 3: //mateAnimals();
        }

    }

    static void init() {
        int players;
        int totalTurns;
        //welcome
        Dialogs.clear();
        System.out.println("      Welcome to: \n \u001B[1mEXTINCT ANIMAL TRADER\033[0;0m \n ----------------------\n");
        var input = Dialogs.promptInt("Press 1 for new game:", 1, 1);
        players = Dialogs.promptInt("How many players? (1-4)", 1, 4);

        //add players
        for (int i = 0; i < players; i++ ) {
            Player player = new Player(Dialogs.prompt("Player "  + (i + 1) + " name?"),i);
            Game.players.add(player);
        }

        //select number of game turns
        totalTurns = Dialogs.promptInt("Select Game duration? (5-30 turns):", 5,30);
        Game.turn(players, totalTurns);
    }
}

