package de.unipassau;

/**
 * @author Nicolas Salgado
 * @version 1.0
 * <p>
 * Csv class
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Csv {

    /**
     * Method to read the csv file into ArrayList of type Categories
     *
     * @param path
     *            userInput of filePath from mainclass
     * @param seperator
     *            userInput of seperator from mainclass
     * @return categoriesImport ArrayList of type Categories with data from csv file
     * @throws IncorrectNumberOfColumnsException
     *             if file has not more than 4 columns
     */
    public static ArrayList<Categories> importFile(String path, String seperator)
            throws IncorrectNumberOfColumnsException {
        ArrayList<Categories> importCategories = new ArrayList<>();
        Path pathToFile = Paths.get(path);

        // try to read all lines from file into Arraylist
        try (BufferedReader br = Files.newBufferedReader(pathToFile);) {
            LineNumberReader lineNumberReader = new LineNumberReader(Files.newBufferedReader(pathToFile));
            int currentLineNumber = lineNumberReader.getLineNumber();
            int startLine = 0;
            String line = br.readLine();

            while (currentLineNumber < startLine) {
                currentLineNumber++;
            }
            while (line != null) {
                String[] valueOfLine = line.split(seperator);
                // throw exception if file has more than 4 columns
                if (valueOfLine.length > 4) {
                    throw new IncorrectNumberOfColumnsException("Datei hat zu viele Spalten!");
                }
                Categories readCategories = defineColumns(valueOfLine);
                importCategories.add(readCategories);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Fehler beim lesen der Datei!");
            e.printStackTrace();
        }
        return importCategories;
    }

    /**
     * Method to define all columns of csv file in ArrayList
     *
     * @param fields
     *            String array with data from importFile
     * @return Object Categories with productCategory, region, year and revenue
     */
    private static Categories defineColumns(String[] fields) {
        String productCategory = fields[0];
        String region = fields[1];
        int year = Integer.parseInt(fields[2]);
        double revenue = Double.parseDouble(fields[3]);
        return new Categories(productCategory, region, year, revenue);
    }

    /**
     * Method to write ArrayList of type Categories into csv file
     *
     * @param filePath
     *            userInput of filePath from mainclass
     * @param seperator
     *            userInput of filePath from mainclass
     * @param actualArray
     *            ArrayList with actual data to export
     * @return actualArray ArrayList with actual data to export
     */
    public static ArrayList<Categories> exportFile(String filePath, String seperator,
                                                   ArrayList<Categories> actualArray) {

        // try to write all lines from Arraylist into file
        BufferedWriter outputWriter = null;
        try {
            outputWriter = new BufferedWriter(new FileWriter(filePath));
        } catch (IOException e) {
            System.out.println("Fehler beim lesen der Datei!");
            e.printStackTrace();
        }
        for (Categories categories : actualArray) {
            // try to write all categories from line to line
            try {
                outputWriter.write(categories.productCategory + seperator + categories.region + seperator
                        + categories.year + seperator + categories.revenue);
                outputWriter.newLine();
            } catch (IOException e1) {
                System.out.println("Fehler beim schreiben der Datei!");
                e1.printStackTrace();
            }
        }
        // try to close bufferedwriter
        try {
            outputWriter.flush();
            outputWriter.close();
        } catch (IOException e2) {
            System.out.println("Fehler beim schließen des writers!");
            e2.printStackTrace();
        }
        return actualArray;

    }

}
