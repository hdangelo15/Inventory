package Model;

/**
 * Constructs and controls Outsourced Parts.
 * @author Hayley D'Angelo
 * */

public class Outsourced extends Part{

    //FIELDS
    /**
     * The name of the company that produced the part.
     * */
    private String companyName;


    //METHODS
    /**
     * The constructor that creates a new instance of an Outsourced Part.
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param companyName
     * */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Gets the company name from an outsourced part instance.
     * @return companyName
     * */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the company name for an outsourced part instance.
     * @param companyName
     * */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
