package com.kashyapbari.gameoflife.main;

import com.kashyapbari.gameoflife.Universe;
import com.kashyapbari.gameoflife.util.parser.UniverseParser;
import com.kashyapbari.gameoflife.util.parser.exception.ParserException;

import java.util.Scanner;

public class main {

    public static String getUserInput(Scanner scanner){
        String line = scanner.nextLine();
        String inputString = "";
        while(!line.isBlank()){
            inputString = inputString +"\n"+line;
        }
        return inputString;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your initial Universe state");
        String universeStateInput = getUserInput(scanner);
        try {
            Universe universe = new UniverseParser().parse(universeStateInput);
            String esc = "";
            while (!esc.equals("quit")){
                universe = new Universe(universe.getAlive()).playTick(1);
                System.out.println("Press Enter for next Tick or enter 'quit'");
                esc = scanner.nextLine().strip();
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }
}
