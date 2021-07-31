package bitforgesys;

import java.util.Scanner;

/**
 * This class represents the Stage A of the project and is used to perform
 * operations applicable on that stage
 *
 * @author
 */
public class StageA {

    // Scanner object to be used as input source through out the stage
    static Scanner inputSource;
    // a print queue instance to be used for the simulation of operations
    PrintQueue printQueue;

    /**
     * This is main gateway to execute the Stage A
     *
     * @param args representing any command line arguments passed
     */
    public static void main(String[] args) {
        // intializing the scanner object to get input from keyboard
        inputSource = new Scanner(System.in);
        // creating instance of Stage A
        StageA demo = new StageA();
        // Taking size of queue in question 
        System.out.print("Enter the size of the Job queue:");
        String input = inputSource.nextLine();
        int size = Integer.parseInt(input);
        // initializing print queue with given size
        demo.printQueue = new PrintQueue(size);
        // Displaying menu for the Stage
        demo.menu();
    }

    /**
     * Method responsible for displaying menu and taking input to redirect the
     * user to operations
     */
    private void menu() {

        // running the loop for menu
        while (true) {

            System.out.print("*********************************** ");
            System.out.print("Bit Forge System Menu ");
            System.out.println(" ***********************************");
            System.out.println("1- Add a print job to the back of the queue");
            System.out.println("2- List print ID, customer ID, description of jobs");
            System.out.println("3- Search for Print Job");
            System.out.println("4- Remove the job at front of queue");
            System.out.println("0- Exit");
            System.out.print("\nYour choice:");
            // Taking user's choice for what he/she wants to do
            String s = inputSource.nextLine();
            int choice = Integer.parseInt(s);
            // Calling operations according to choice
            switch (choice) {
                // if choice is 1 go to addJob() method for letting user to add a job
                case 1:
                    addJob();
                    break;
                // If choice is 2 go to listDetails() method to list details of jobs
                case 2:
                    listDetails();
                    break;
                // If choice is 3, go to searchJob() method for performing search operation
                case 3:
                    searchJob();
                    break;
                // If choice is 4,go to removeJob() method for performing remove operation
                case 4:
                    removeJob();
                    break;

                // If choice is 0, display the message and quit
                case 0:
                    System.out.print("***************************");
                    System.out.print("Thank you for using our application, quitting");
                    System.out.println("***************************\n\n");
                    System.exit(0);
                    break;
                // Otherwise, notify that input was invalid
                default:
                    System.out.println("Invalid choice. \n\n");

            }

        }
    }

    /**
     * Method for letting user add new job at back of the queue
     */
    private void addJob() {
        // Displaying message
        System.out.println("********** Adding a Job **********");
        // Taking job description as input from user
        System.out.print("Enter Job description:");
        String description = inputSource.nextLine();
        // Taking job's print quantity as input from user
        System.out.print("Enter print quantity:");
        String input = inputSource.nextLine();
        int quantity = Integer.parseInt(input);

        // Taking job's print volume as input from user
        System.out.print("Enter print volume:");
        input = inputSource.nextLine();
        double volume = Integer.parseInt(input);

        // Taking job's plastic type as input from user
        System.out.print("Enter plastic type i.e. PLA (default),ABS or Nylon:");
        String plasticType = inputSource.nextLine();

        // If user have not chosen the ABS or NYLON as plastic type, user PLA as plastic type by default
        if (!plasticType.equalsIgnoreCase("abs") && !plasticType.equalsIgnoreCase("nylon")) {
            plasticType = "PLA";
        }

        // Creating print job instance with inputs we got from user
        PrintJob pj = new PrintJob(this.printQueue.currentPrintJobID,
                "C131A", description, quantity, volume, plasticType);

        // Adding new job object to the queue
        this.printQueue.add(pj);
        // displaying message after successfully adding
        System.out.println("******** Successfully added the job. ********\n\n");

    }

    /**
     * Method to be used for displaying specific details for all jobs in the
     * queue
     */
    private void listDetails() {
        System.out.println("********** Listing Details **********");
        System.out.println("PrintJob ID\tCustomer ID\tDescription");
        // looping through the queue
        for (int i = 0; i < this.printQueue.size; i++) {
            // if object is not null
            if (this.printQueue.p[i] != null) {
                // Print the detials of the job
                this.printQueue.printDetails(this.printQueue.p[i]);
            }
        }
        System.out.println("\n\n");
    }

    /**
     * Method to be used for searching a job with ID and print specific details
     * for that job in the queue
     */
    private void searchJob() {
        System.out.println("********** Searching a Job **********");
        // Get the print ID from user for the job he/she wants to search
        System.out.print("Enter print Id of job you are searching:");
        String input = inputSource.nextLine();
        int printId = Integer.parseInt(input);
        // Loop through the queue
        for (int i = 0; i < this.printQueue.size; i++) {
            // If item in queue is not null
            if (this.printQueue.p[i] != null) {
                // If ID of item in queue matches with the ID from the user
                if (this.printQueue.p[i].printId == printId) {
                    // print details of that job
                    this.printQueue.p[i].displayAllDetails();
                    return;

                }
            }
        }
        // If program doesn't return from above, it means there was no job found with that ID
        System.out.println("******** No job exists with requested Print ID.********  \n\n");
    }

    /**
     * Method to be used for removing a job at front of the queue
     *
     */
    private void removeJob() {
        // Displaying message
        System.out.println("********** Removing a Job **********");
        // Getting the job removed from the queue
        PrintJob pj = this.printQueue.remove();
        // printing detials of the removed job
        if (pj != null) {
            this.printQueue.printDetails(pj);
            System.out.println("******** Above job removed successfully. ********\n\n");
        } else {
            System.out.println("******** No job to remove in the queue ********\n\n");
        }

    }

}
