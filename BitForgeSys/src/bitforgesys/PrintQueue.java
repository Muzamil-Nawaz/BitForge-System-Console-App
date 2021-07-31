package bitforgesys;

/**
 * This class simulates the operations of storing the jobs in queue
 * (first-in-first-out) functionality with required methods
 *
 * @author
 */
public class PrintQueue {

    // 1D array for storing PrintJob objects
    public PrintJob[] p;
    // This represents the counter tracking the print job ID for next job to come, starting from 100
    int currentPrintJobID = 100;
    // Variable for storing size of the queue
    public int size;

    /**
     * Constructor for initializing the 1D array (queue) with given size
     *
     * @param size storing size of queue of jobs
     */
    public PrintQueue(int size) {
        this.size = size;
        p = new PrintJob[this.size];
    }

    /**
     * Method to be used for printing any job details
     *
     * @param printId
     */
    public void printJob(int printId) {
        // Iterating through the queue
        for (int i = 0; i < size; i++) {
            // If job's id matches with passed job id
            if (p[i].printId == printId) {
                // Display details of that job
                p[i].displayAllDetails();
                // break the loop when found the job
                break;
            }
        }
    }

    /**
     * Method to be used for adding any new job to the queue
     *
     * @param pj representing new print job object to be added to queue
     */
    public void add(PrintJob pj) {
        // Setting id for new print job with print job tracker
        pj.printId = currentPrintJobID;
        // Setting dummy customer id for the job
        pj.customerId = "C131A";
        // calculating final price for the new job by using it's method
        pj.calcTotalPrice();
        // Incrementing job id counter for new job
        currentPrintJobID++;
        // Looping through queue to go to next open position
        for (int i = 0; i < this.size; i++) {
            // Add the new job where there is place in back of queue
            if (p[i] == null) {
                p[i] = pj;
                // break the loop after adding the job
                return;
            }
        }

    }

    /**
     * This method is to be used for removing the job object at the front of the
     * queue
     *
     * @return the removed job object, null otherwise
     */
    public PrintJob remove() {
        // loop through the queue
        for (int i = 0; i < this.size; i++) {
            // when first job found at the front
            if (p[i] != null) {
                // remove it
                PrintJob pj = p[i];
                p[i] = null;
                // Reshuffling the items after removal of the first item
                for (int j = 0; j < this.size-1; j++) {
                    p[j] = p[j+1];
                }
                // return the removed job
                return pj;

            }
        }
        // if no job removed for any reason, return null
        return null;
    }

    /**
     * This method is to be used to search for print job with specific id
     *
     * @param printId storing id for job to search for
     * @return job if found, null otherwise
     */
    public PrintJob searchJob(int printId) {
        // Loop through the queue
        for (int i = 0; i < size; i++) {
            // If any job's print id matches the given print id
            if (p[i].printId == printId) {
                // return that job object
                return p[i];
            }
        }
        // If no job found, return null
        return null;
    }

    /**
     * This method is used to print specific details for any given print job
     *
     * @param pj storing the print job, for which details need to be printed
     */
    public void printDetails(PrintJob pj) {
        // Printing print id, custoemr id and description
        System.out.println(pj.printId + "\t\t" + pj.customerId + "\t\t" + pj.shortDescription);

    }
}
