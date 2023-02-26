package Model;

/**
 * Constructs and controls InHouse Parts.
 * @author Hayley D'Angelo
 * */
public class InHouse extends Part{

    //FIELDS
    /**
     * The ID of the machine that produced the part.
     * */
    private int machineid;

    //METHODS
    /**
     * Constructs a new instance of an InHouse Part.
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param machineid
     * */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineid){
        super(id, name, price, stock, min, max);
        this.machineid = machineid;
    }

    /**
     * Gets the machine ID from an InHouse Part instance.
     * @return machineid
     * */
    public int getMachineid() {
        return machineid;
    }

    /**
     * Sets the machine ID for an InHouse Part instance.
     * @param machineid
     * */
    public void setMachineid(int machineid) {
        this.machineid = machineid;
    }
}
