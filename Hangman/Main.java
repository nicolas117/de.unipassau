/**
 * @author Nicolas Salgado
 * @version 1.0
 *
 * The game hangman implemented in Java
 *
 */

import java.util.Scanner;

public class Main {

    // variables for errors and keyboard Input
    public static int errorPoints = 0;
    private static Scanner keyBoard;

    /**
     * Main method with switch-case for commands: letter, word, errorpoints,
     * difficulty, quit
     *
     * @param args
     *            parameter
     */
    public static void main(String[] args) {

        String wordToFound = args[0];
        wordToFound = wordToFound.toLowerCase();
        char[] actualFound = new char[wordToFound.length()];

        char[] charWord = wordToFound.toCharArray();
        char[] underscore = Methods.toUnderscoreArray(wordToFound);

        keyBoard = new Scanner(System.in);
        MorseCode.difficulty(wordToFound);

        while (true) {
            System.out.println(
                    "Was möchten Sie tun? Sie haben folgende Möglichkeiten: \nWollen Sie einen Buchstaben raten ('l'), ein Wort raten ('w'),\nden aktuellen Fehlerpunktestand abrufen ('p'), die Schwierigkeit ausgeben ('c') oder das Spiel beenden ('q') ");
            String command = keyBoard.nextLine();
            // switch to prove the command
            switch (command) {
                case "l":
                    System.out.println("Buchstaben raten 'l' ");
                    actualFound = Methods.guessLetter(charWord, underscore, errorPoints);
                    System.out.println(actualFound);
                    Methods.checkErrorPoints(errorPoints);
                    break;
                case "w":
                    System.out.println("Wort raten 'w' ");
                    Methods.guessWholeWord(wordToFound, errorPoints);
                    Methods.checkErrorPoints(errorPoints);
                    break;
                case "p":
                    System.out.println("Aktuellen Fehlerpunktestand abrufen 'p' ");
                    Methods.showErrorPoints(errorPoints);
                    break;
                case "c":
                    MorseCode.difficulty(wordToFound);
                    break;
                case "q":
                    System.out.println("Spiel wird beendet");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Falscher Befehl");
                    break;
            }
        }

    }

}
