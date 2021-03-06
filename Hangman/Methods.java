/**
 * @author Nicolas Salgado
 * @version 1.0
 *
 * Methods class
 *
 */

import java.util.Arrays;
import java.util.Scanner;

public class Methods {

    static Scanner keyBoard = new Scanner(System.in);

    /**
     * Method to fill char array dependent from inputWordlength with underscore
     *
     * @param inputWord
     *            String to get parameter
     * @return changedWord char array with underscore
     */
    public static char[] toUnderscoreArray(String inputWord) {

        char[] changedWord = new char[inputWord.length()];
        Arrays.fill(changedWord, '_');
        return changedWord;
    }

    /**
     * Method to get letter from keyboard and compare it with inputWord, dependent
     * from inputwordlength If letter is found, override underscore at given place
     * If letter is not found, write out erroroutput and count errorPoints++ if word
     * is found, programm will be exited
     *
     * @param guessWord
     *            char[] parameter
     * @param store
     *            char[] to save actual status of letter found
     * @param errorCounter
     *            int to get errorPoints
     * @return store found letters
     */
    public static char[] guessLetter(char[] guessWord, char[] store, int errorCounter) {
        String input = keyBoard.nextLine();
        String letterInput = input.toLowerCase();
        char guessInput = letterInput.charAt(0);

        if (Arrays.toString(guessWord).contains(letterInput)) {
            for (int i = 0; i < guessWord.length; i++) {
                if (guessInput == guessWord[i]) {
                    if (guessInput == guessWord[0]) {
                        guessInput = Character.toUpperCase(guessInput);
                        guessWord[0] = Character.toUpperCase(guessWord[0]);
                        store[i] = guessInput;
                    }
                    store[i] = guessInput;
                }

            }
            if (Arrays.equals(store, guessWord)) {
                successGame(errorCounter);
                System.exit(0);
            }
        } else {
            System.out.println("Tut mir leid, der Buchstabe ist nicht enthalten. Sie erhalten einen Fehlerpunkt.");
            Main.errorPoints++;
        }

        return store;
    }

    /**
     * Method to get word from keyboard and compare it with inputWord, dependent
     * from inputwordlength If word is not found, write out erroroutput and count
     * errorPoints++ if word is found, programm will be exited
     *
     * @param inputWord
     *            String parameter
     * @param errorCounter
     *            int to get errorPoints
     * @return inputWord parameter
     *
     */
    public static String guessWholeWord(String inputWord, int errorCounter) {
        System.out.println("Ok, bitte geben Sie das Wort ein: ");
        String wordScanner = keyBoard.nextLine();
        String wordInput = wordScanner.toLowerCase();
        if (wordInput.equals(inputWord)) {
            successGame(errorCounter);
            System.exit(0);
        } else {
            System.out.println("Tut mir leid, falsches Wort. Sie erhalten einen Fehlerpunkt.\n");
            Main.errorPoints++;

        }
        return inputWord;
    }

    /**
     * Method to printout success information
     *
     * @param errorCounter
     *            int to get errorPoints
     */

    public static void successGame(int errorCounter) {

        if (errorCounter == 1) {
            System.out.println(
                    "Herzlichen Glückwunsch! Sie haben mit " + errorCounter + " Fehlerpunkt das Wort errraten!");
        } else
            System.out.println(
                    "Herzlichen Glückwunsch! Sie haben mit " + errorCounter + " Fehlerpunkten das Wort errraten!");
    }

    /**
     * Method to get actual errorpoints
     *
     * @param errorCounter
     *            int to get errorPoints
     * @return errorCounter errorPoints
     */

    public static int showErrorPoints(int errorCounter) {

        if (errorCounter == 1) {
            System.out.println("Ihr Fehlerpunktestand beträgt " + errorCounter + " Punkt.\n");
        } else
            System.out.println("Ihr Fehlerpunktestand beträgt " + errorCounter + " Punkte.\n");
        return errorCounter;
    }

    /**
     * Method to exit programm if errorpoints are 10
     *
     * @param counter
     *            int to get errorPoints
     */
    public static void checkErrorPoints(int counter) {

        if (counter == 10) {
            System.out.println("Spiel wird beendet! Sie haben die maximale Fehlerpunktzahl erreicht!");
            System.exit(0);
        }

    }

}
