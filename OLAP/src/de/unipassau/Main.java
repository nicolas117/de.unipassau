package de.unipassau;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Nicolas Salgado
 * @version 1.0
 * <p>
 * Main class
 */

public class Main {

    static Scanner keyBoard = null;

    /**
     * Main method with whileloop for console output and opertions
     *
     * @param args
     *            filePath and seperator
     * @throws FileNotFoundException
     *             if csv is not found
     */
    public static void main(String[] args) throws FileNotFoundException {

        keyBoard = new Scanner(System.in);
        String filePath = args[0];
        String seperator = args[1];

        ArrayList<Categories> actualData = new ArrayList<>();
        // try to read revenues.csv with runconfiguration arguments
        try {
            actualData = Csv.importFile(filePath, seperator);
        } catch (IncorrectNumberOfColumnsException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        // run whileloop for all usercommands: dicing, analyze, sort, print, importFile,
        // exportFile and exit
        while (true) {
            System.out.println(
                    "\nWas möchten Sie tun? Sie haben folgende Möglichkeiten: \nWollen Sie ein Dicing vornehmen (\"d\")\nDaten analysieren (\"a\")\nDaten sortieren (\"s\")\naktuelle Daten ausgeben (\"p\")\neine CSV-Datei importieren (\"i\")\neine CSV-Datei exportieren (\"e\")\noder das Spiel beenden (\"q\") ");
            String command = keyBoard.nextLine();
            switch (command) {
                case "d":
                    System.out.println("Dicing \"d\" ");
                    actualData = Methods.dicing(actualData);
                    break;
                case "a":
                    System.out.println("Daten analysieren \"a\" ");
                    Methods.analyze(actualData);
                    break;
                case "s":
                    System.out.println("Daten sortieren \"s\" ");
                    Methods.sortData(actualData);
                    break;
                case "p":
                    System.out.println("aktuelle Daten ausgeben \"p\" ");
                    Methods.printData(actualData);
                    break;
                case "i":
                    // get path and seperator from userinput
                    System.out.println("CSV-Datei importieren \"i\" ");
                    System.out.println("Bitte geben Sie den Pfad an: ");
                    filePath = keyBoard.nextLine();
                    System.out.println("Bitte geben Sie den Seperator an: ");
                    seperator = keyBoard.nextLine();

                    try {
                        actualData = Csv.importFile(filePath, seperator);
                    } catch (IncorrectNumberOfColumnsException e) {
                        System.out.println(e.getMessage());
                        System.exit(1);
                    }
                    break;
                case "e":
                    // get path and seperator from userinput
                    System.out.println("CSV-Datei exportieren \"e\" ");
                    System.out.println("Bitte geben Sie den Pfad an: ");
                    filePath = keyBoard.nextLine();
                    System.out.println("Bitte geben Sie den Seperator an: ");
                    seperator = keyBoard.nextLine();
                    Csv.exportFile(filePath, seperator, actualData);
                    break;
                case "q":
                    System.out.println("Programm wird beendet!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Falscher Befehl");
                    break;
            }
        }

    }

}
