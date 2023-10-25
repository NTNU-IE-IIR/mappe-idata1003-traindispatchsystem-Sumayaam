package edu.ntnu.stud;

import java.sql.SQLOutput;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Represents the User Interface of the Student Application.
 * The User Interface (UI) is a text based menu controlled UI.
 */
public class TrainDispatchAppUi {
    private TrainDispatchRegister trainDispatchRegister;

    // A String constant holding the current version of the application.
    private static final String version = "v0.1-SNAPSHOT";

    /**
     * Creates an instance of TrainDispatch.
     */
    public TrainDispatchAppUi() {
        this.trainDispatchRegister = new TrainDispatchRegister();
    }

    public void init() {
        System.out.println("Welcome to the Train Dispatch App!");
        System.out.println("******* TrainDispatchApp **********");


    }

    /**
     * Prints the details about a Train departure to the console.
     *
     * @param  trainDeparture train  to print the information about.
     */
    private void printTrainDepartureInfo(TrainDeparture trainDeparture) {
        System.out.println("Scheduled time of departure: " + trainDeparture.getDepartureTime());
        System.out.println("Line: " + trainDeparture.getLine());
        System.out.println("Train Number: " + trainDeparture.getTrainNumber());
        System.out.println("Delay: " + trainDeparture.getDelay());
        System.out.println("Track " + trainDeparture.getTrack());

    }

    /**
     * Prints all students in the register to the console.
     */
    public void printAllTrainDepartures() {
        //Iterator<TrainDeparture> it = this.trainRegister.iterator();
       // while (it.hasNext()) {
          //  TrainDeparture trainDeparture = it.next();
            //printTrainDepartureInfo(trainDeparture);


        }

    //start method

    public void start() {
            //this.trainRegister.fillRegisterWithDefaultTrainDepartures();
            System.out.println("***** Train Dispatch Register " + version + "*****");
            System.out.println();
            System.out.println("Welcome this application!");

            System.out.println("Number of train departures: " + (0));

            boolean finished = false;
            while (!finished) {
                // Ask the user to create new train departures
                // until he/she is done adding train departures..
                System.out.println("Do you want to add a new train departure (y/n)?");
                Scanner inputScanner = new Scanner(System.in);

                String userInput = inputScanner.nextLine();

                if (userInput.toUpperCase().startsWith("Y")) {
                    // Create new student from user input
                    TrainDeparture TrainDepartureToAdd = createTrainDepartureFromUser(inputScanner);
                    // Add the student to the register
                   // this.trainRegister.addStudent(TrainDepartureToAddToAdd);
                } else {
                    // User is done adding train departure
                    finished = true;
                }
            }

            // Print all train departures in the register
            printAllTrainDepartures();
            System.out.println("Thank you for using this Train Register App. Bye!");
        }

        /**
         * Creates a new student from user input.
         *
         * @param inputScanner the scanner to get the user input from
         * @return the student created based on the user input
         */
        private static TrainDeparture createTrainDepartureFromUser(Scanner inputScanner) {
            // Create new student from user input
            // Gather information about the student to create
            System.out.println("Please enter the scheduled time of departure: ");
            String departureTime = inputScanner.nextLine();
            System.out.println("Please enter the line: ");
            String line  = inputScanner.nextLine();
            System.out.println("Please enter the train number: ");
            String trainNumber = inputScanner.nextLine();
            System.out.println("Please enter the destination: ");
            String destination = inputScanner.nextLine();
            System.out.println("Please enter the track delay (hh.mm): ");
            String delay = inputScanner.nextLine();
            System.out.println("Please enter the track information: ");
            String track = inputScanner.nextLine();



            // Create the train departure from the information above
            TrainDeparture traindeparturetoAdd = new Traindeparture (LocalTime.parse(departureTime), line, trainNumber,
                    destination, track);
            return traindeparturetoAdd;
        }


        }





