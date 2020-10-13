package com.company;
import java.util.ArrayList;


public class Game {
    public static ArrayList<Player> players = new ArrayList<>();
    public static boolean actionsTaken;
    public int turn;
    public int totalTurns;

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
                System.out.println("Game turn: \u001B[1m" + turn + "/" + totalTurns +"\033[0;0m");
                player.showStatsNAnimals();
                playerAction(player);
            }
        }

    }

    static public void playerAction(Player player) {
        //while (Game.actionsTaken = false){
        var input = Dialogs.promptInt("\nChoose your action:\n [1.Visit Store] [2.Feed Animals] [3.Mate Animals]"
                , 1, 3);

        switch (input) {
            case 1 -> {
                Store.storeFront(player);
                break;
            }
            case 2 -> System.out.println("2"); //feedAnimals();
            case 3 -> {
                mateAnimals(player);
                break;
            }
        }
        //}
    }

    static void init() {
        int players;
        int totalTurns;
        //welcome
        Dialogs.clear();
        System.out.println("      Welcome to: \n \u001B[1mEXTINCT ANIMAL TRADER\033[0;0m \n ----------------------\n");
        Dialogs.promptInt("Press 1 for new game:", 1, 1);
        players = Dialogs.promptInt("How many players? (1-4)", 1, 4);

        //add players
        for (int i = 0; i < players; i++) {
            Player player = new Player(Dialogs.prompt("Player " + (i + 1) + " name?"));
            Game.players.add(player);
        }

        //select number of game turns
        totalTurns = Dialogs.promptInt("Select Game duration? (5-30 turns):", 5, 30);
        Game.turn(players, totalTurns);
    }

    static void mateAnimals(Player player) {
        Dialogs.clear();
        player.showStatsNAnimals();
        //setup mating variables
        int mate1 = Dialogs.promptInt("First animal to mate:",
                1, player.animalInv.size() + 1) - 1;
        int mate2 = Dialogs.promptInt("Second animal to mate:",
                1, player.animalInv.size() + 1) - 1;
        Object animalType1 = player.animalInv.get(mate1).getClass().getSimpleName();
        Object animalType2 = player.animalInv.get(mate2).getClass().getSimpleName();
        String gender1 = player.animalInv.get(mate1).gender;
        String gender2 = player.animalInv.get(mate2).gender;
        //try to mate
        if (!(mate1 == mate2) && (animalType1 == animalType2) && !(gender1 == gender2)) {
            boolean rand = Math.random() < 0.5;
            if (rand) {
                boolean rand2 = Math.random() < 0.5;
                String gender;
                if (rand2) {gender = "male";}
                else {gender = "female";}
                String name = Dialogs.prompt("Success!! It's a " + gender + "! Choose baby animal name:");
                Store.buyMammoths(player, 0, name, gender);
                Game.actionsTaken = true;
            }
            else {
                System.out.println("There was no baby :´(");
                Game.actionsTaken = true;
                Dialogs.enterToContinue();
            }

        }
        if (mate1 == mate2) {
            System.out.println("These animals are not hermaphrodites!");
            Dialogs.enterToContinue();
        }
        if (gender1 == gender2) {
            System.out.println("Only male + female can mate!");
            Dialogs.enterToContinue();
        }

    }

}

