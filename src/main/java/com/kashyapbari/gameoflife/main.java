package com.kashyapbari.gameoflife;

import com.kashyapbari.gameoflife.Universe;
import com.kashyapbari.gameoflife.util.parser.UniverseParser;
import com.kashyapbari.gameoflife.util.parser.exception.ParserException;

import java.util.Scanner;
import java.util.stream.Collectors;

public class main {

    public static String getUserInput(Scanner scanner){
        String line = scanner.nextLine();
        String inputString = "";
        while(!line.isEmpty() || !line.isBlank() ){
            inputString = inputString +"\n"+line;
            line = scanner.nextLine();
        }
        return inputString;
    }

    public static String stringUniverseState(Universe universe){
        return universe.getAlive().getSet()
                .stream()
                .map(cell -> cell.stringValue())
                .collect(Collectors.joining("\n"));

    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your initial Universe state, leave a blank line to finish input");
        String universeStateInput = getUserInput(scanner);
        try {
            Universe universe = new UniverseParser().parse(universeStateInput);
            String esc = "";
            while (!esc.equals("quit")){
                universe = new Universe(universe.getAlive()).playTick(1);
                System.out.println(stringUniverseState(universe));
                System.out.println("Press Enter for next Tick or enter 'quit'");
                esc = scanner.nextLine().strip();
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }
}
