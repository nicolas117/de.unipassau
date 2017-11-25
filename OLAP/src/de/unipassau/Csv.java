package de.unipassau;

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

	public static ArrayList<Categories> importFile(String path, String seperator)
			throws IncorrectNumberOfColumnsException {
		ArrayList<Categories> categoriesImport = new ArrayList<>();
		Path pathToFile = Paths.get(path);

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
				if (valueOfLine.length > 4) {
					throw new IncorrectNumberOfColumnsException("Datei hat zu viele Spalten!");
				}
				Categories categoriesExport = defineColumns(valueOfLine);
				categoriesImport.add(categoriesExport);
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Fehler beim lesen der Datei!");
			e.printStackTrace();
		}
		return categoriesImport;
	}

	private static Categories defineColumns(String[] fields) {
		String productCategory = fields[0];
		String region = fields[1];
		int year = Integer.parseInt(fields[2]);
		double revenue = Double.parseDouble(fields[3]);
		return new Categories(productCategory, region, year, revenue);
	}

	public static ArrayList<Categories> exportFile(String filePath, String seperator,
			ArrayList<Categories> actualArray) {

		BufferedWriter outputWriter = null;
		try {
			outputWriter = new BufferedWriter(new FileWriter(filePath));
		} catch (IOException e1) {
			System.out.println("Fehler beim lesen der Datei!");
			e1.printStackTrace();
		}
		for (Categories categories : actualArray) {
			try {

				outputWriter.write(categories.productCategory + seperator + categories.region + seperator
						+ categories.year + seperator + categories.revenue);
				outputWriter.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		try {
			outputWriter.flush();
			outputWriter.close();
		} catch (IOException e) {
			System.out.println("Fehler beim lesen der Datei!");
			e.printStackTrace();
		}
		return actualArray;

	}

}
