/**
 * @author Nicolas Salgado
 * @version 1.0
 *
 * Morsecode class
 *
 */

import java.util.HashMap;

public class MorseCode {

    /**
     * Create String dependent from inputword for comparison with difficulty
     *
     * @param inputWord
     *            String to get parameter
     * @return strB StringBuffer as a String
     */
    public static String createStringBuffer(String inputWord) {

        char[] c = new char[inputWord.length()];
        for (int i = 0; i < inputWord.length(); i++) {
            c[i] = inputWord.charAt(i);
        }

        StringBuffer strB = new StringBuffer();
        for (char m : c) {
            strB.append(hmap.get(m));
        }
        return strB.toString();

    }

    /**
     * Calcuation of difficulty dependent from morsecodelength
     *
     * @param calculateWord
     *            String to get parameter
     */
    public static void difficulty(String calculateWord) {

        createMorseCode();
        String composite = createStringBuffer(calculateWord);
        int difficulty = composite.length();
        System.out.println("Das zu erratende Wort hat eine Schwierigkeit von " + difficulty + ".\n");
    }

    static HashMap<Character, String> hmap = new HashMap<Character, String>();

    /**
     * Hashmap to create morsecode for each letter
     *
     */
    public static void createMorseCode() {
        hmap.put('a', "·−");
        hmap.put('b', "−···");
        hmap.put('c', "−·−·");
        hmap.put('d', "−··");
        hmap.put('e', "·");
        hmap.put('f', "··−·");
        hmap.put('g', "−−·");
        hmap.put('h', "····");
        hmap.put('i', "··");
        hmap.put('j', "·−−−");
        hmap.put('k', "−·−");
        hmap.put('l', "·−··");
        hmap.put('m', "−−");
        hmap.put('n', "−·");
        hmap.put('o', "−−−");
        hmap.put('p', "·−−·");
        hmap.put('q', "−−·−");
        hmap.put('r', "·−·");
        hmap.put('s', "···");
        hmap.put('t', "−");
        hmap.put('u', "··−");
        hmap.put('v', "···−");
        hmap.put('w', "·−−");
        hmap.put('x', "−··−");
        hmap.put('y', "−·−−");
        hmap.put('z', "−−··");

        // HashMap for special character
        hmap.put('ä', ".-.-");
        hmap.put('ö', "---.");
        hmap.put('ü', "..--");
        hmap.put('ß', "...--..");

    }

}
