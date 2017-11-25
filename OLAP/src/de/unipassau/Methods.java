package de.unipassau;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author Nicolas Salgado
 * @version 1.0
 * <p>
 * Methods class
 */

public class Methods {

    /**
     * Dicing method to first get three categories (productcategory, region and year) from user and compare given categories over bulk operation with csvfile
     *
     * @param dicingCategories ArrayList of type Categories with actual csvfile
     * @return filterCategories ArrayList of type Categories with actual data after dicing
     */
    public static ArrayList<Categories> dicing(ArrayList<Categories> dicingCategories) {
        ArrayList<String> filterByProductCategory = new ArrayList<>();
        ArrayList<String> filterByRegion = new ArrayList<>();
        ArrayList<Integer> filterByYear = new ArrayList<>();
        Supplier<Stream<Categories>> stream = () -> dicingCategories.parallelStream();
        boolean empty = false;

        Scanner keyBoard = new Scanner(System.in);
        System.out.println("Bitte geben Sie die Produktkategorie ein: ");

        while (empty != true) {
            String productCategory = keyBoard.nextLine().toLowerCase();

            if (productCategory.isEmpty()) {
                System.out.println("Bitte geben Sie die Region ein: ");
                while (empty != true) {

                    String region = keyBoard.nextLine().toLowerCase();

                    if (region.isEmpty()) {
                        System.out.println("Bitte geben Sie das Jahr ein: ");
                        while (empty != true) {

                            String year = keyBoard.nextLine();

                            if (year.isEmpty()) {

                                empty = true;
                            } else {
                                int parseYear;
                                parseYear = Integer.parseInt(year);

                                filterByYear.add(parseYear);
                            }
                        }

                    } else {
                        filterByRegion.add(region);
                    }

                }
            } else {
                filterByProductCategory.add(productCategory);

            }

        }

        ArrayList<Categories> filterCategories = new ArrayList<>();
        for (String filterProductCat : filterByProductCategory) {

            for (String filterRegion : filterByRegion) {
                for (int filterYear : filterByYear) {
                    stream.get().forEach(e -> e.getProductCategory());
                    Stream<Categories> filteredResult = stream.get()
                            .filter(e -> e.getProductCategory().toLowerCase().contains(filterProductCat))
                            .filter(e -> e.getRegion().toLowerCase().contains(filterRegion))
                            .filter(e -> e.getYear() == filterYear);
                    filteredResult.forEach(e -> filterCategories.add(e));

                }
            }
        }

        printData(filterCategories);
        return filterCategories;

    }

    /**
     * Analyze method to get all regions with higher turnover then userinput
     *
     * @param analyzeRevenue ArrayList of type Categories with actual data
     */

    public static void analyze(ArrayList<Categories> analyzeRevenue) {
        ArrayList<Categories> listOfRevenue = new ArrayList<>();
        ArrayList<String> filteredRegions = new ArrayList<>();
        Scanner keyBoard2 = new Scanner(System.in);
        System.out.println("Bitte geben Sie den Mindesumsatz ein: ");
        String minSales = keyBoard2.nextLine();
        double parseMinSales = Double.parseDouble(minSales);

        Supplier<Stream<Categories>> stream = () -> analyzeRevenue.parallelStream();
        Stream<Categories> filterRevenue = stream.get().filter(e -> e.getRevenue() >= parseMinSales);
        filterRevenue.forEach(e -> listOfRevenue.add(e));
        listOfRevenue.forEach(e -> filteredRegions.add(e.getRegion()));
        removeDuplicatedRegions(filteredRegions);
        filteredRegions.forEach(System.out::println);

    }

    /**
     * Method to remove duplicatedRegions for analyzemethod
     *
     * @param duplicatedRegions ArrayList of type String with data from analyzemethod
     * @return duplicatedRegions List of all singleentries regions
     */
    public static ArrayList<String> removeDuplicatedRegions(ArrayList<String> duplicatedRegions) {
        HashSet<String> hashSet = new HashSet<String>(duplicatedRegions);
        duplicatedRegions.clear();
        duplicatedRegions.addAll(hashSet);

        return duplicatedRegions;
    }

    /**
     * Print method for methods which prints a table of actual data
     *
     * @param printTable ArrayList of type Categories with actual data
     */
    public static void printData(ArrayList<Categories> printTable) {
        System.out.println("Produkt\t" + "\tRegion\t " + "\tJahr\t" + "Wert");
        for (Iterator<Categories> iterator = printTable.iterator(); iterator.hasNext(); ) {
            Categories cat = iterator.next();
            System.out.print(cat.getProductCategory() + "\t\t");
            System.out.print(cat.getRegion() + "\t\t");
            System.out.print(cat.getYear() + "\t");
            System.out.print(cat.getRevenue() + "\t\n");
        }
    }

    /**
     * Sort method for all categories. User can give up to three dimensions as input, which will be compared as sort parameter
     *
     * @param sortCategories ArrayList of type Categories with actual data
     * @return analyzeRevenue ArrayList of type Categories with actual data after sort
     */
    public static ArrayList<Categories> sortData(ArrayList<Categories> sortCategories) {
        ArrayList<String> dimensions = new ArrayList<>();
        Scanner keyBoard3 = new Scanner(System.in);
        boolean numberOfDimensions = false;

        System.out.println("Bitte geben Sie die Dimension ein: ");
        while (numberOfDimensions != true && dimensions.size() < 4) {
            String category = keyBoard3.nextLine();
            if (!category.isEmpty()) {
                dimensions.add(category);
            } else {

                numberOfDimensions = true;
            }
        }
        Comparator<Categories> byProductCategory;
        Comparator<Categories> byRegion;
        Comparator<Categories> byYear;

        Comparator<Categories> byProductCategoryAndRegion;
        Comparator<Categories> byProductCategoryAndYear;
        Comparator<Categories> byRegionAndYear;
        Comparator<Categories> byRegionAndProductCategory;
        Comparator<Categories> byYearAndRegion;
        Comparator<Categories> byYearAndProductCategory;

        Comparator<Categories> byProductCategoryRegionAndYear;
        Comparator<Categories> byProductCategoryYearAndRegion;
        Comparator<Categories> byRegionYearAndProductCategory;
        Comparator<Categories> byRegionProductCategoryAndYear;
        Comparator<Categories> byYearRegionAndProductCategory;
        Comparator<Categories> byYearProductCategoryAndRegion;

        if (dimensions.size() == 1) {

            if (dimensions.get(0).equals("produktkategorie")) {
                byProductCategory = Comparator.comparing(Categories::getProductCategory);
                sortCategories.sort(byProductCategory);
            } else if (dimensions.get(0).contains("region")) {
                byRegion = Comparator.comparing(Categories::getRegion);
                sortCategories.sort(byRegion);
            } else if (dimensions.get(0).equals("jahr")) {
                byYear = Comparator.comparing(Categories::getYear);
                sortCategories.sort(byYear);

            }
        } else {

            if (dimensions.size() == 2) {
                if (dimensions.get(0).contains("produktkategorie") && dimensions.get(1).contains("region")) {
                    byProductCategoryAndRegion = Comparator.comparing(Categories::getProductCategory)
                            .thenComparing(Categories::getRegion);
                    sortCategories.sort(byProductCategoryAndRegion);
                } else if (dimensions.get(0).contains("produktkategorie") && dimensions.get(1).contains("jahr")) {
                    byProductCategoryAndYear = Comparator.comparing(Categories::getProductCategory)
                            .thenComparing(Categories::getYear);
                    sortCategories.sort(byProductCategoryAndYear);
                } else if (dimensions.get(0).contains("region") && dimensions.get(1).contains("jahr")) {
                    byRegionAndYear = Comparator.comparing(Categories::getRegion).thenComparing(Categories::getYear);
                    sortCategories.sort(byRegionAndYear);
                } else if (dimensions.get(0).contains("region") && dimensions.get(1).contains("produktkategorie")) {
                    byRegionAndProductCategory = Comparator.comparing(Categories::getRegion)
                            .thenComparing(Categories::getProductCategory);
                    sortCategories.sort(byRegionAndProductCategory);
                } else if (dimensions.get(0).contains("jahr") && dimensions.get(1).contains("region")) {
                    byYearAndRegion = Comparator.comparing(Categories::getYear).thenComparing(Categories::getRegion);
                    sortCategories.sort(byYearAndRegion);
                } else if (dimensions.get(0).contains("jahr") && dimensions.get(1).contains("produktkategorie")) {
                    byYearAndProductCategory = Comparator.comparing(Categories::getYear)
                            .thenComparing(Categories::getProductCategory);
                    sortCategories.sort(byYearAndProductCategory);

                }
            } else {

                if (dimensions.size() == 3) {
                    if (dimensions.get(0).contains("produktkategorie") && dimensions.get(1).contains("region")
                            && dimensions.get(2).contains("jahr")) {
                        byProductCategoryRegionAndYear = Comparator.comparing(Categories::getProductCategory)
                                .thenComparing(Categories::getRegion).thenComparing(Categories::getYear);
                        sortCategories.sort(byProductCategoryRegionAndYear);
                    } else if (dimensions.get(0).contains("produktkategorie") && dimensions.get(1).contains("jahr")
                            && dimensions.get(2).contains("region")) {
                        byProductCategoryYearAndRegion = Comparator.comparing(Categories::getProductCategory)
                                .thenComparing(Categories::getYear).thenComparing(Categories::getRegion);
                        sortCategories.sort(byProductCategoryYearAndRegion);
                    } else if (dimensions.get(0).contains("region") && dimensions.get(1).contains("jahr")
                            && dimensions.get(2).contains("produktkategorie")) {
                        byRegionYearAndProductCategory = Comparator.comparing(Categories::getRegion)
                                .thenComparing(Categories::getYear).thenComparing(Categories::getProductCategory);
                        sortCategories.sort(byRegionYearAndProductCategory);
                    } else if (dimensions.get(0).contains("region") && dimensions.get(1).contains("produktkategorie")
                            && dimensions.get(2).contains("jahr")) {
                        byRegionProductCategoryAndYear = Comparator.comparing(Categories::getRegion)
                                .thenComparing(Categories::getProductCategory).thenComparing(Categories::getYear);
                        sortCategories.sort(byRegionProductCategoryAndYear);
                    } else if (dimensions.get(0).contains("jahr") && dimensions.get(1).contains("region")
                            && dimensions.get(2).contains("produktkategorie")) {
                        byYearRegionAndProductCategory = Comparator.comparing(Categories::getYear)
                                .thenComparing(Categories::getRegion).thenComparing(Categories::getProductCategory);
                        sortCategories.sort(byYearRegionAndProductCategory);
                    } else if (dimensions.get(0).contains("jahr") && dimensions.get(1).contains("produktkategorie")
                            && dimensions.get(2).contains("region")) {
                        byYearProductCategoryAndRegion = Comparator.comparing(Categories::getYear)
                                .thenComparing(Categories::getProductCategory).thenComparing(Categories::getRegion);
                        sortCategories.sort(byYearProductCategoryAndRegion);
                    }
                }

            }

        }
        printData(sortCategories);
        return sortCategories;

    }
}
