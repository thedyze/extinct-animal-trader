package com.company;
import java.util.*;

public class Dialogs {

    static private Scanner scanner = new Scanner(System.in);

    static public void clear(){
        // "clear" the console by printing 60 new lines
        System.out.println("\n".repeat(60));
    }

    static public String prompt(String question){
        // clear() ? maybe we want a clear before each prompt
        System.out.println(question);
        return scanner.nextLine();
    }

    static public int promptInt(String question, int min, int max){
        var num = min - 1;
        try {
            num = Integer.parseInt(prompt(question));
        }
        catch(Exception ignore){}
        // if illegal choice show the prompt again (recursion)
        // otherwise return the choice
        return num < min || num > max ?
                promptInt(question, min, max) : num;
    }
    static public void enterToContinue()
    {
        System.out.println("Press [Enter] to continue..");
        scanner.nextLine();
    }

}
