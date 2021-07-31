package bitforgesys;

/**
 * This class represents a entity of PrintJob in Stage A of the project with
 * required attributes, with methods to calculate price and display details.
 *
 * @author
 */
public class PrintJob {

    // Variable used for storing print id of the print job object
    protected int printId;
    // Variable used for storing customer id of the print job object
    protected String customerId;
    // Variable used for storing short description of the print job object
    protected String shortDescription;
    // Variable used for storing print quantity of the print job object
    protected int printQuantity;
    // Variable used for storing volume in Cmm of the print job object
    protected double volumeInCmm;
    // Variable used for storing plastic type of the print job object
    protected String plasticType;
    // Variable used for storing price of the print job object
    protected double price;

    /**
     * Empty constructor for empty object-initialization where needed
     */
    public PrintJob() {
    }

    /**
     * Parametrized constructor to directly initialize the attributes with
     * passed values
     *
     * @param printId storing the print id passed from the caller
     * @param customerId storing the customer id passed from the caller
     * @param shortDescription storing the short description passed from the
     * caller
     * @param printQuantity storing the print quantity passed from the caller
     * @param volumeInCmm storing the volume of print job passed from the caller
     * @param plasticType storing the plastic type passed from the caller
     */
    public PrintJob(int printId, String customerId, String shortDescription,
            int printQuantity, double volumeInCmm, String plasticType) {
        this.printId = printId;
        this.customerId = customerId;
        this.shortDescription = shortDescription;
        this.printQuantity = printQuantity;
        this.volumeInCmm = volumeInCmm;
        this.plasticType = plasticType;
        this.price = this.calcTotalPrice();
    }

    /**
     * This method is used to calculate the final price of the print job based
     * on it's attributes
     *
     * @return final calculated price
     */
    public double calcTotalPrice() {
        // if plastic type is PLA
        if (this.plasticType.equalsIgnoreCase("PLA")) {
            // calculate price for 5 cents per CMM for each unit
            price = volumeInCmm * 5 * printQuantity;
        } // if plastic type is ABS
        else if (this.plasticType.equalsIgnoreCase("ABS")) {
            // calculate price for 6 cents per CMM for each unit
            price = volumeInCmm * 6 * printQuantity;
        } // if plastic type is Nylon
        else if (this.plasticType.equalsIgnoreCase("NYLON")) {
            // calculate price for 7 cents per CMM for each unit
            price = volumeInCmm * 7 * printQuantity;
        }

        //converting price into dollars
        price = price / 100;

        // If price is less than $60.50
        if (price < 60.50) {
            // then set the price to $60.50 anyway to cover fixed overheads
            price = 60.50;
        }
        // Setting price of current print job object to calculated price
        this.price = price;

        // returning the calculated price
        return this.price;
    }

    /**
     * This method is used to print details (attributes) of the print job object
     */
    public void displayAllDetails() {
        System.out.println("" + "printId=" + printId + ", customerId=" + customerId
                + ", shortDescription=" + shortDescription + ", printQuantity=" + printQuantity + ", "
                + "volumeInCmm=" + volumeInCmm + ", plasticType=" + plasticType + ", price=" + price + " dollars");
    }

}
