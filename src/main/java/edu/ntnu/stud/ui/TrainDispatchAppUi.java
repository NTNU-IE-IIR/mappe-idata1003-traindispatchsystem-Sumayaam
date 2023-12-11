package edu.ntnu.stud.ui;

import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.entity.TrainStationClock;
import edu.ntnu.stud.logic.TrainDispatchRecord;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * The TrainDispatchAppUi class represents the user interface of the application.
 *
 * <p>This class takes care of all interaction with the user,
 * including showing of menu choices, collection of user inputs,
 * and printing of information to the user.
 * The information which is printed is the choices they make from the menu,
 * which includes listing of train departures, adding new train departures,
 * assigning track and/or delay, updating the clock,
 * and searching for train departures by train number or destination.<p/>
 *
 * <p>It also makes sures that there is quality error handling for user inputs</p>
 * <p></p> This class communicates with TrainDispatchRecord
 *to tackle train departures, and TrainStationClock to handle time.</p>
 */

public class TrainDispatchAppUi {
  private TrainDispatchRecord trainDispatchRecord;

  private TrainStationClock trainDispatchClock;

  // A String constant holding the current version of the application.
  private static final String version = "v0.1-SNAPSHOT";


  /**
   * Creates an instance of TrainDispatchApp.
   */
  private static final int LIST_ALL_TRAIN_DEPARTURES_BY_DEPARTURE_TIME = 1;
  private static final int ADD_NEW_TRAIN_DEPARTURE = 2;
  private static final int ASSIGN_TRACK = 3;

  private static final int ADD_DELAY = 4;

  private static final int SEARCH_BY_TRAIN_NUMBER = 5;
  private static final int SEARCH_BY_DESTINATION = 6;

  private static final int UPDATE_CLOCK = 7;

  private static final int EXIT = 10;
  private static final int MAX_MENU_CHOICE = 10;

  public TrainDispatchAppUi() {
    // Intentionally empty
  }

  /**
   * initializes the application
   *
   * <p>Creates a new instance of TrainDispatchRecord.
   */
  public void init() {
    this.trainDispatchRecord = new TrainDispatchRecord();
    this.trainDispatchClock = new TrainStationClock();
  }

  /**
   * Starts the application. This is the main entry point where the program execution begins.
   *
   * <p>The method will first show the welcome screen, and then show the menu.
   */

  public void start() {

    boolean finished = false;
    while (!finished) {
      printWelcomeScreen();
      displayMenu();
      int selectedMenu = getUsersMenuChoice();
      if (!executeMenuChoice(selectedMenu)) {
        finished = true;
      }
    }
    System.out.println("Thank you for using this Train Dispatch Record. Bye!");
  }

  /**
   * Asks the user for which menu choice to execute.
   * <p> If the user enters a valid menu choice, the method will return the selected menu choice.
   * If the user enters an invalid menu choice, the method will return -1.</p>
   *
   * @return the menu choice by the user, or -1 if the user entered an invalid menu choice.
   */
  private int getUsersMenuChoice() {
    int selectedMenu; // creates a variable to hold the selected menu choice
    Scanner inputScanner = new Scanner(System.in); // scanner object to read input from user.
    System.out.println("Please enter your choice between 1 and: " + MAX_MENU_CHOICE + ": ");

    if (inputScanner.hasNextInt()) {  // checks if user has entered an int
      selectedMenu = inputScanner.nextInt(); // reads int and gets saved in 'selectedMenu'.
    } else {
      selectedMenu = -1; //if not an int that is an option in the menu, return -1.
    }
    return selectedMenu;
  }

  /**
   * Prints the welcome screen to the user.
   */
  private void printWelcomeScreen() {
    System.out.println("\n\n*****  Train Dispatch Record" + version + " *****");
    System.out.println("           Welcome to this application!\n");
    System.out.println();
  }

  /**
   * Displays the menu to the user.
   * <p>The menu </p>
   */
  private void displayMenu() {
    System.out.println("1. List all train departures sorted by departure time.");
    System.out.println("2. Add new train departure.");
    System.out.println("3. Assign track.");
    System.out.println("4. Add delay.");
    System.out.println("5. Search by train number.");
    System.out.println("6. Search by destination.");
    System.out.println("7. Update clock.");
    System.out.println("10. Exit application.");

  }

  /**
   * Executes the menu choice that the user selected.
   * <p> if the user selected a valid menu choice, the method will execute the corresponding action,
   * and {@code true} will be returned. The options are methods that are created in the Ui</p>
   *
   * <p>If the user decides to exit the application,{@code false} will be returned </p>
   *
   * @param selectedMenu the selected menu choice
   * @return false if user wants to exit the application, otherwise true
   */

  private boolean executeMenuChoice(int selectedMenu) {
    boolean result = true;

    switch (selectedMenu) {
      case LIST_ALL_TRAIN_DEPARTURES_BY_DEPARTURE_TIME:
        this.listAllTrainDeparturesSortedByDepartureTime();
        break;
      case ADD_NEW_TRAIN_DEPARTURE:
        this.addNewTrainDeparture();
        break;

      case ASSIGN_TRACK:
        this.addTrack();
        break;

      case ADD_DELAY:
        this.addDelay();
        break;

      case SEARCH_BY_TRAIN_NUMBER:
        this.searchByTrainNumber();
        break;

      case SEARCH_BY_DESTINATION:
        this.searchByDestination();
        break;

      case UPDATE_CLOCK:
        this.handleClockUpdate();
        break;

      case EXIT:
        result = false;
        break;

      default:
        System.out.println("\n Please enter a number between 1 and " + MAX_MENU_CHOICE);
        System.out.println("Invalid menu choice. Please try again.");
        break;
    }
    return result;
  }

  /**
   * Adding delay to a train departure by searching for the train number.
   * <p> This method asks the user for a train number for
   * the train departure they want to add a delay to. Then it will ask to add delay.
   * An exception is thrown({@code DateTimeParseException} ) if the delay is invalid
   * (not in hh:mm)
   * </p> it then searches for the train departure with
   * the given train number in the "trainDispatchRecord".
   * If no matching train departure found, a message will be printed to the console.
   * On the other hand if a train departure is found, the delay will be added.
   * <p>Copilot and ChatGPT was used to help me finish the code</p>
   *
   * @throws DateTimeParseException if the delay is invalid.
   */

  private void addDelay() {
    final Scanner delayScanner = new Scanner(System.in);
    //creating a scanner object to read user input.
    int searchingTrainNumber =
        this.correctTrainNumberError(); // using the method to correct input error for train number.

    System.out.println("Please enter delay in hh:mm: ");
    LocalTime parsedDelay = null; // creating a variable which holds the parsed delay.
    boolean validDelay = false;
    while (!validDelay) { // a while loop which checks if the delay is valid.
      String delay = delayScanner.nextLine();
      try {
        parsedDelay = LocalTime.parse(delay); // parsing the delay, to check if it is valid.
        validDelay = true;
      } catch (DateTimeParseException e) { // handles exception if the delay is invalid,
        // and prints a message to user.
        System.out.println("Invalid delay. Please try again.");
      }
    }

    TrainDeparture trainDeparture = this.trainDispatchRecord
        .findTrainDepartureByTrainNumber(searchingTrainNumber);
    // searching after a train departure with a given train number
    if (trainDeparture == null) {
      System.out.println("No train departures found with train number: " + searchingTrainNumber);
    } else {
      trainDeparture.setDelay(parsedDelay); //adds the delay if the train number is not in use,
      // if not it will exit and print message.
      // Better than looping until correct, because user can remember wrong and want to exit.

    }

  }

  /**
   * Assigns a track to a train departure by searching for the train number.
   * <p> This method starts by asking user for a valid existing train number
   * It then asks for the track user wants to assign. It then searches for a train departure
   * in the record class by using the train number user wrote
   * </p> If a correct train departure is found the track is updated.
   * If train departure not found by train number, a message is printed.
   * <p> To ensure a valid user input, the method 'correctTrainNumberError is used</p>
   * <p>Copilot and chatGPT was used to help me finish the code</p>
   */

  private void addTrack() {
    //Gets correct train number form user by using correctTrainNumberError method.
    int searchInTrainNumber = this.correctTrainNumberError();
    //Gets a valid track from user using correctTrack method
    int track = this.correctTrack();
    //searches for train departure with help from the record-class
    TrainDeparture trainDeparture = this.trainDispatchRecord
        .findTrainDepartureByTrainNumber(searchInTrainNumber);
    //if a train departure is found, the track info is updated
    if (trainDeparture != null) {
      trainDeparture.setTrack(track);
    } else {
      //if no train departure found, a message is printed. Not looping entering correct train number
      // same reasoning as written in add delay method.
      System.out.println("No train departures found with train number: " + searchInTrainNumber);
    }
  }

  /**
   * Handles the clock update, and throws an exception if the time is invalid.
   * <p> If the time is invalid, a message will be printed to the console.</p>
   * <p> If the time is valid, the clock will be updated to the new time.</p>
   * <p> To ensure a valid user input, the method 'correctTrainNumberError is used</p>
   * <p>Copilot was used to help me finish the code</p>
   *
   * @throws IllegalArgumentException if the time is invalid.
   */

  private void handleClockUpdate() {
    Scanner clockScanner = new Scanner(System.in); //Scanner object created to read input from user
    LocalTime parsedTime = null;
    //defines variable parsedTime, to keep track of the parsed time
    String newTime;
    boolean validTime = false; //check if the time from user is valid
    while (!validTime) {
      System.out.println("Please enter the new time in hh:mm: ");
      newTime = clockScanner.nextLine();
      try {
        parsedTime = LocalTime.parse(newTime);
        validTime = true;
      } catch (DateTimeParseException e) {
        System.out.println("Invalid time. Please try again.");
      }
    } // Loop until valid time is written, if not DatetimeParseException is used
    this.updateClock(parsedTime); // calls on the updateClock method, with the parsed time
    System.out.println("The clock has been updated to: " + parsedTime); // updates clock

  }

  /**
   * Updates the clock to the given time taking the removedTrainDepartures method into account.
   * <p> This method sets the current time to the new time in for the user to see in option 7
   * It then calls a method which removes all train departures before
   * the time of the station</p>
   * <p>Copilot was used to help me finish the code</p>
   *
   * @param newTime the new time the clock is updated to.
   */
  private void updateClock(LocalTime newTime) {
    this.trainDispatchClock.setCurrentTime(newTime); //sets current time to new time
    this.removeDepartedTrainDepartures(); // calls a method to remove train departures
    // based on newTime.
  }

  /**
   * Removes a train departure from the register.
   * <p> If no train departures are found, a message will be printed to the console.</p>
   *
   * <p> If train departures are found, the information about the train departures
   * will be printed to the console.</p>
   * <p>To ensure a valid user input, the method 'correctTrainNumberError is used</p>
   * <p>Copilot was used to help me finish the code</p>
   */
  private void removeDepartedTrainDepartures() {
    LocalTime currentTime = this.trainDispatchClock.getCurrentTime();
    //Gets current time from trainDispatchClock-class.
    int removedDepartures = trainDispatchRecord.removeTrainDeparturesFromBefore(currentTime);
    //removes train departures from trainDispatchRecord that has departure before current time.
    //returns number of removed.
    if (removedDepartures == 0) {
      System.out.println("No train departures removed");
    } else {
      System.out.println("Removed " + removedDepartures + " train departures");
    } // prints the appropriate message if a train departure is removed or not.
  }


  /**
   * Prints the information about a train departure.
   * <p> This method takes a train departure object as a parameter and
   * writes detailed information about he given train departure,
   * which includes scheduled departure time,
   * and actual departure time with delay, train line, train number, destination and track </p>
   *
   * @param trainDeparture a train departure to print information about.
   *                       <p>Copilot was used to help me finish the code</p>
   */
  private void printTrainDepartureInfo(TrainDeparture trainDeparture) {
    // prints the info about departure time
    System.out.println("Scheduled time of departure: " + trainDeparture.getDepartureTime());
    // calculates departure time with delay, and prints it.
    try {
      System.out.println("Actual time of departure: "
          + trainDeparture.getDepartureTime().plusHours(trainDeparture.getDelay().getHour())
          .plusMinutes(trainDeparture
              .getDelay().getMinute()));
    } catch (DateTimeException e) { // handles exception if the delay is invalid,
      System.out.println("Actual time of departure: " + trainDeparture.getDepartureTime());
    }
    System.out.println("Line of train: " + trainDeparture.getLine()); //prints the line of the train
    System.out.println("Train number: " + trainDeparture.getTrainNumber());
    //prints the train number
    System.out.println("Destination: " + trainDeparture.getDestination().toUpperCase());
    //prints the destination, using toUpperCase to make it easier to search in the record.
    if (trainDeparture.getDelay() != (LocalTime.parse("00:00"))) {
      System.out.println("Delay: " + trainDeparture.getDelay());
      // print delay only if it's later than 00:00
    }
    //prints the track of the train, if it is assigned. If not, it will not print anything.
    if (trainDeparture.getTrack() != -1) {
      System.out.println("Track of train:" + trainDeparture.getTrack());
    }

  }

  /**
   * Searches for train departures by destination.
   * <p> This method uses user's destination input, and searches after this destination</p>
   * <p> If no train departures are found, a message will be printed to the user.</p>
   * <p> If train departures are found,
   * the information about the train departures will be printed.</p>
   *
   * <p>To ensure a valid user input, the method 'correctDestinationError is used</p>
   * <p>Copilot was used to help me finish the code</p>
   */
  private void searchByDestination() {
    String searchDestination = this.correctDestinationError();
    // Checks if the destination is valid, if it's invalid a message is printed to user.
    TrainDeparture trainDeparture =
        this.trainDispatchRecord.findTrainDepartureByDestination(searchDestination);
    if (trainDeparture == null) {
      System.out.println("No train departures found with destination: " + searchDestination);
    } else {
      System.out.println("Train departures found with destination: " + searchDestination);
      this.printTrainDepartureInfo(trainDeparture);
    }
  }

  /**
   * Searches for train departures by train number.
   * <p>if no train departures are found, a message will be printed to the console.</p>
   * <p>if train departures are found,
   * the information about the train departures will be printed to the console.</p>
   * <p>To ensure a valid user input, the method 'correctTrainNumberError is used</p>
   * <p>Copilot was used to help me finish the code</p>
   */
  private void searchByTrainNumber() {
    int searchTrainNumber = this.correctTrainNumberError();
    // checks if the train number is valid, if it's invalid a message is printed to user.
    TrainDeparture trainDeparture =
        this.trainDispatchRecord.findTrainDepartureByTrainNumber(searchTrainNumber);
    if (trainDeparture == null) {
      System.out.println("No train departures found with train number: " + searchTrainNumber);
    } else {
      System.out.println("Train departures found with train number: " + searchTrainNumber);
      this.printTrainDepartureInfo(trainDeparture);
    }
  }

  /**
   * Lists all train departures sorted by departure time.
   * <p> This method gets a list for all train departures from the register class,
   * sorted after departure time</p>
   * <p> If no train departures are found, nothing will come up</p>
   * <p> If there are info about train departure using the method 'printTrainDepartureInfo' </p>
   * <p>
   * Copilot was used to help me finish the code</p>
   * </p>
   */
  private void listAllTrainDeparturesSortedByDepartureTime() {
    // Goes through every train departure in the list from the record class,
    // sorted in departure time.
    for (TrainDeparture trainDeparture : this.trainDispatchRecord
        .getTrainDeparturesSortedByDepartureTime()) {
      this.printTrainDepartureInfo(trainDeparture);
      // prints info


    }
  }


  /**
   * Adds a train departure to the register by asking the user for the information about the
   * train departures.
   * <p> The method asks the user first for departure time,
   * line, train number, destination, delay and track.
   * Then it does a validation check for each of the fields</p>
   *
   * <p>After the information needed is collected and validated,
   * a new TrainDeparture-object is created, which is then added to the record.
   *
   * <p>To ensure a valid user input, the methods 'correctTrainNumberError'.
   *   <p>Copilot was used to help me finish the code</p>
   */
  private void addNewTrainDeparture() {
    final Scanner inputScanner = new Scanner(System.in);

    // Handles the collection and validation of the departure time.
    LocalTime parsedDepartureTime;
    boolean validDepartureTime = false;

    String departureTime = null;
    while (!validDepartureTime) {
      System.out.println("Please enter departure time in hh:mm: ");
      departureTime = inputScanner.nextLine();
      try {
        parsedDepartureTime = LocalTime.parse(departureTime);
        if (parsedDepartureTime.isAfter(this.trainDispatchClock.getCurrentTime())) {
          validDepartureTime = true;
        } else {
          System.out.println("Departure time is before current time. Please try again.");
        }
      } catch (DateTimeParseException e) {
        System.out.println("Invalid departure time. Please try again.");
      }
    } //Used Copilot to finish the code to use while

    // Handles the collection and validation of the line.
    System.out.println("Please enter line of train: ");
    boolean validLine = false;
    String line = null;
    while (!validLine) {
      line = inputScanner.nextLine();
      if (line.isBlank()) {
        System.out.println("Invalid line. Please try again.");
      } else {
        validLine = true;
      }
    } // used copilot to finish the code


    // handles the collection and validation of the train number.
    int trainNumber = this.correctTrainNumberError();
    while (this.trainDispatchRecord.findTrainDepartureByTrainNumber(trainNumber) != null
        || this.trainDispatchRecord.getUsedTrainNumbers().contains(trainNumber)) {
      System.out.println("Train number already in use. Please enter new.");
      trainNumber = this.correctTrainNumberError();

    } //Used copilot to finish this code

    // handles the collection and validation of the destination
    //uses a method to correct input error for destination.

    String destination = this.correctDestinationError();
    //used copilot to finish code


    // handles the collection and validation of the delay.
    boolean validDelay = false;
    String delay = null;
    while (!validDelay) {
      System.out.println("Please enter delay in hh:mm: ");
      delay = inputScanner.nextLine();
      try {
        validDelay = true;
      } catch (DateTimeParseException e) {
        System.out.println("Invalid delay. Please try again.");
      }
    }

    // Handles the collection and validation of the departure time.
    int track = this.correctTrack();

    // Creates a new TrainDeparture-object and adds it to the record.
    TrainDeparture trainDepartureToAdd = new TrainDeparture(LocalTime.parse(departureTime),
        line,
        trainNumber,
        destination,
        LocalTime.parse(delay),
        track);
    this.trainDispatchRecord.addTrainDeparture(trainDepartureToAdd);
  }

  /**
   * A method which handles invalid input for train number.
   *
   * <p> This method asks the user to a enter a train number.
   * If the train number is not a positive int, an error message will come up, and it
   * will ask the user to enter a new number.
   * It will loop until correct train number is written </p>
   * <p></p>
   * <p>Copilot was used to help me finish the code</p>
   *
   * @return valid train number.
   */

  private int correctTrainNumberError() {
    final Scanner inputScanner = new Scanner(System.in);
    int trainNumber = 0;
    boolean validTrainNumber = false;
    // loops until valid number is written
    while (!validTrainNumber) {
      System.out.println("Please enter the train number: ");
      if (inputScanner.hasNextInt()) {

        int givenTrainNumber = inputScanner.nextInt();
        validTrainNumber = true;

        if (givenTrainNumber > 0) {
          trainNumber = givenTrainNumber;
        } else {
          System.out.println("Invalid train number. Please try again.");
        }
      } else { // writes an error message if it's not an int
        System.out.println("Invalid train number. Please try again.");
        inputScanner.nextLine();
      }

    }
    return trainNumber;
  }

  /**
   * A method which handles invalid input for track.
   * <p> If the user enters an invalid track, a message will be printed to the console.</p>
   * <p> This methods is a loop which makes sure that the user enters a valid track (int)
   * If not an error message will be prints, and user gets asked to write valid track </p>
   * <p>Copilot was used to help me finish the code</p>
   *
   * @return valid track.
   */

  private int correctTrack() {
    final Scanner trackScanner = new Scanner(System.in);
    int track = 0;
    boolean validTrack = false;

    while (!validTrack) {
      System.out.println("Please enter the track: ");
      if (trackScanner.hasNextInt()) {
        int givenTrack = trackScanner.nextInt();
        if (givenTrack >= 0) {
          track = givenTrack;
          validTrack = true;
        } else {
          System.out.println("Invalid track. Please try again.");

        }
      } else {
        System.out.println("Invalid track. Please try again.");
        trackScanner.nextLine();
      }
      // used copilot to finish the code

    }
    return track;
  }

  /**
   * Corrects input error for destination.
   * <p></p>
   * <p> If the user enters an invalid destination, a message will be printed to the console.</p>
   *
   * <p>This method is a loop which makes sure that the user enter.
   * a valid destination(String), which cannot be null or Blank.
   * <p>Copilot was used to help me finish the code</p>
   *
   * @return valid destination.
   */

  private String correctDestinationError() {
    final Scanner inputScanner = new Scanner(System.in);
    String destination = null;
    boolean validDestination = false;

    while (!validDestination) {
      System.out.println("Please enter the destination: ");
      destination = inputScanner.nextLine().toUpperCase();
      if (destination.isBlank()) {
        System.out.println("Invalid destination. Please try again.");
      } else {
        validDestination = true;
      }
    }
    return destination;
  }
}






