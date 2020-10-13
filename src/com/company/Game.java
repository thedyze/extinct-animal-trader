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
                player.showCashNAnimals();
                //System.out.println("Turn: " + turn + "/" + totalTurns +". Player: " + player.getName() + ", you have "
                //        + player.getCash() + "€.");
                playerAction(player);
            }
        }

    }
    static public void playerAction(Player player) {
        //while (Game.actionsTaken = false){
            var input = Dialogs.promptInt("\nChoose your action:\n [1:Visit Store] [2:Feed Animals] [3:Mate Animals]"
                    , 1, 3);

            switch (input) {
                case 1 -> {Store.storeFront(player); break;}
                case 2 -> System.out.println("2"); //feedAnimals();
                case 3 -> {mateAnimals(player); break;}
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
        for (int i = 0; i < players; i++ ) {
            Player player = new Player(Dialogs.prompt("Player "  + (i + 1) + " name?"));
            Game.players.add(player);
        }

        //select number of game turns
        totalTurns = Dialogs.promptInt("Select Game duration? (5-30 turns):", 5,30);
        Game.turn(players, totalTurns);
    }
    static void mateAnimals(Player player) {
        player.showCashNAnimals();
        int mate1 = Dialogs.promptInt("First animal you want to use for mating:",
                1,player.animalInv.size()+1);
        int mate2 = Dialogs.promptInt("Second animal you want to use for mating:",
                1,player.animalInv.size()+1);
        Object aType1 = player.animalInv.get(mate1-1).getClass().getSimpleName();
        Object aType2 = player.animalInv.get(mate2-1).getClass().getSimpleName();
        if (!(mate1 == mate2) && (aType1 == aType2)) {
            System.out.println("trying to mate");
            String name = Dialogs.prompt("Success! Choose baby animal name:");
            Store.buyMammoths(player,0,name,"male");
            Game.actionsTaken = true;

        }
        if (mate1 == mate2) {System.out.println("These animals are not hermaphrodites!");}

    }

}

