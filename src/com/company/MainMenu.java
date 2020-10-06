package com.company;
import java.util.*;

public class MainMenu {

    public static void init() {
        Dialogs.clear();
        System.out.println("      Welcome to: \n \u001B[1mEXTINCT ANIMAL TRADER\033[0;0m \n ----------------------\n" +
                        "Press 1 for new game");
        var age = Dialogs.promptInt(":>", 1, 1);
    }

}
