package bitforgesys;

/**
 * This class represents a entity of ProcessedPrintJob in Stage B as a child
 * class of PrintJob of the project with required attributes, with methods to
 * calculate price and display details.
 *
 * @author
 */
public class ProcessedPrintJob extends PrintJob {

    // Extra attributes needed for processed print job in addition to base class' attributes
    private boolean uvCuringApplied, uvCoatingApplied, polishingApplied;

    /**
     * Parametrized constructor for initializing processed print job object
     *
     * @param uvCuringApplied indicating if UV curing needs to be applied or not
     * @param uvCoatingApplied indicating if UV coating needs to be applied or
     * not
     * @param polishingApplied indicating if polishing needs to be applied or
     * not
     * @param printId storing the print id passed from the caller
     * @param customerId storing the customer id passed from the caller
     * @param shortDescription storing the short description passed from the
     * caller
     * @param printQuantity storing the print quantity passed from the caller
     * @param volumeInCmm storing the volume of print job passed from the caller
     * @param plasticType storing the plastic type passed from the caller
     */
    public ProcessedPrintJob(boolean uvCuringApplied, boolean uvCoatingApplied,
            boolean polishingApplied, int printId, String customerId,
            String shortDescription, int printQuantity, double volumeInCmm, String plasticType) {
        super(printId, customerId, shortDescription, printQuantity, volumeInCmm, plasticType);
        this.uvCuringApplied = uvCuringApplied;
        this.uvCoatingApplied = uvCoatingApplied;
        this.polishingApplied = polishingApplied;
    }

    /**
     * Method for getting state of UV curing
     *
     * @return value of uvCurringApplied flag
     */
    public boolean isUvCuringApplied() {
        return uvCuringApplied;
    }

    /**
     * This is for setting value of uvCuringApplied flag for processed print job
     * where needed
     *
     * @param uvCuring storing value to be set
     */
    public void setUvCuring(boolean uvCuring) {
        this.uvCuringApplied = uvCuring;
    }

    /**
     * Method for getting state of UV coating
     *
     * @return value of uvCoatingApplied flag
     */
    public boolean isUvCoatingApplied() {
        return uvCoatingApplied;
    }

    /**
     * This is for setting value of uvCoatingApplied flag for processed print
     * job where needed
     *
     * @param uvCoating storing value to be set
     */
    public void setUvCoating(boolean uvCoating) {
        this.uvCoatingApplied = uvCoating;
    }

    /**
     * Method for getting state of polishing
     *
     * @return value of polishingApplied flag
     */
    public boolean isPolishingApplied() {
        return polishingApplied;
    }

    /**
     * This is for setting value of polishingApplied flag for processed print
     * job where needed
     *
     * @param polishing storing value to be set
     */
    public void setPolishing(boolean polishing) {
        this.polishingApplied = polishing;
    }

    /**
     * This method is overriding the super class's calcTotalPrice method to
     * calculate final price for any processed job
     *
     * @return calculated price
     */
    @Override
    public double calcTotalPrice() {
        // Getting the price calculated from the base class
        double price = super.calcTotalPrice();
        // if plastic type was other than base class' options i.e. Acrylic
        if (this.plasticType.equalsIgnoreCase("acrylic")) {
            // Calculate price as 9 cents per CMM for each unit
            price = (9 * this.volumeInCmm * this.printQuantity) / 100.00;
        }

        // If UV curing needs to be applied
        if (this.isUvCuringApplied()) {
            // Increase 1 cent for each CMM in the price
            price += (1 * this.volumeInCmm) / 100.00;
        }
        // If UV coating needs to be applied
        if (this.isUvCoatingApplied()) {
            // Increase 0.5 cent for each CMM in the price
            price += (0.5 * this.volumeInCmm) / 100.00;
        }
        // If polishing needs to be applied
        if (this.isPolishingApplied()) {
            // Increase 50 cents for each CMM in the price per unit
            price += (0.5 * this.printQuantity);
        }

        // If job is for more than 500 items
        if (this.printQuantity > 500) {
            // Apply 10% discount in price
            price = price - (price * .1);
        }

        // Set price attribute of processed print job to calculated price
        this.price = price;

        // retun the price
        return this.price;

    }

    /**
     * This method is overriding the super class' displayAllDetails method to
     * print attributes for any processed job
     *
     */
    @Override
    public void displayAllDetails() {
        // first print initial attributes using super class method
        super.displayAllDetails();
        // then print extra attributes
        System.out.println("uvCuringApplied=" + uvCuringApplied + ", uvCoatingApplied="
                + uvCoatingApplied + ", polishingApplied=" + polishingApplied + "\n\n");
    }

}
