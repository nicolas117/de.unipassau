package de.unipassau;

public class Categories {

    String productCategory;
    String region;
    int year;
    double revenue;

    /**
     * @param productCategory
     * @param region
     * @param year
     * @param revenue
     */
    public Categories(String productCategory, String region, int year, double revenue) {
        super();
        this.productCategory = productCategory;
        this.region = region;
        this.year = year;
        this.revenue = revenue;
    }

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
     * @param category
     * @return
     */
    public int compareTo(Categories category) {
        return productCategory.compareTo(category.getProductCategory());
    }

    /**
     *
     */
    public String toString() {
        return productCategory + "\t" + region + "\t" + year + "\t" + revenue;
    }


}
