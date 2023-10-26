package edu.ntnu.stud;


import java.sql.SQLOutput;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Scanner;

public class TrainDispatchAppUi {
    private TrainDispatchRecord trainDispatchRecord;

    // A String constant holding the current version of the application.
    private static final String version = "v0.1-SNAPSHOT";

    /**
     * Creates an instance of TrainDispatchApp.
     */

    public TrainDispatchAppUi() {
        this.trainDispatchRecord = new TrainDispatchRecord();
    }
/**
 * initializes the application
 *
 */

public void init() {
    System.out.println("Welcome to the train dispatch app!");
    System.out.println("System loading.....");
}
    /**
     * Prints the details about a student to the console.
     *
     * @param trainDeparture the  to print the information about.
     */
    private void presentTrainDepartureInfo(TrainDeparture trainDeparture) {
        System.out.println("Scheduled Time of departure: " + trainDeparture.getDepartureTime());
        System.out.println("Line of train: " + trainDeparture.getLine());
        System.out.println("Train number of train: " + trainDeparture.getTrainNumber());
        System.out.println("Destination of train: "+ trainDeparture.getDestination());
        System.out.println("Delay of train:" + trainDeparture.getDelay());
        System.out.println("Track of train:" + trainDeparture.getTrack());

    }

    /**
     * Presents all train departures in the register to the console.
     */
    // iterator


    /**
     * Starts the application. This is the main entrypoint for the instance of train Dispatch App.
     */
    public void start() {
       // add method

        System.out.println("*****  Train Dispatch Record" + version + " *****");
        System.out.println(); // Prints an empty line
        System.out.println("Welcome to this application!");

        System.out.println("Number of train departures: " +(0));

        boolean finished = false;
        while (!finished) {
            // Ask the user to create new student
            // until he/she is done adding students..
            System.out.println("Do you want to add a new train departure (yes/no)?");
            Scanner inputScanner = new Scanner(System.in);

            String userInput = inputScanner.nextLine();

            if (userInput.toUpperCase().startsWith("Y")) {
                // Then create a new train departure
                TrainDeparture trainDepartureToAdd = createTrainDepartureFromUser(inputScanner);
                // Add the train departure to the record
            } else {
                // User is done adding students
                finished = true;
            }
        }

        // Print all students in the register
       // presentAllTrainDepartureInfo();
        System.out.println("Thank you for using this Train Dispatch Record. Bye!");
    }

    /**
     * Creates a new student from user input.
     *
     * @param inputScanner the scanner to get the user input from
     * @return the train departure created based on the user´s input
     */
    private static TrainDeparture createTrainDepartureFromUser(Scanner inputScanner) {
        // Create new student from user input
        // Gather information about the student to create
        System.out.println("Please The scheduled time of departure in hh:mm: ");
        String departureTime = inputScanner.nextLine();
        System.out.println("Please enter line of train: ");
        String line= inputScanner.nextLine();
        System.out.println("Please enter train number: ");
        String trainNumber = inputScanner.nextLine();
        System.out.println("Please enter destination: ");
        String destination = inputScanner.nextLine();
        System.out.println("Please enter delay in hh:mm: ");
        String delay = inputScanner.nextLine();
        System.out.println("Please enter track: ");
        int track = inputScanner.nextInt();

        // Create the student from the collected information
        TrainDeparture trainDepartureToAdd = new TrainDeparture(LocalTime.parse(departureTime), line, trainNumber, destination,
                track);
        return trainDepartureToAdd;
    }
}
