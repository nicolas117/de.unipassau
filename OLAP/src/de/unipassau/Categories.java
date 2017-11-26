package de.unipassau;

/**
 * @author Nicolas Salgado
 * @version 1.0
 * <p>
 * Categories class
 */

public class Categories {

    String productCategory;
    String region;
    int year;
    double revenue;

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    /**
     * Constructor to get all objects of categories
     *
     * @param productCategory
     *            String of vine, oil or olives
     * @param region
     *            String of germany or austria
     * @param year
     *            int of 2010, 2011, 2012 or 2013
     * @param revenue
     *            double of all turnovers
     */
    public Categories(String productCategory, String region, int year, double revenue) {
        super();
        this.productCategory = productCategory;
        this.region = region;
        this.year = year;
        this.revenue = revenue;
    }

}
