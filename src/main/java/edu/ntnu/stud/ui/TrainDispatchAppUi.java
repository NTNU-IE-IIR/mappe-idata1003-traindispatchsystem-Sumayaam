package edu.ntnu.stud.ui;


import edu.ntnu.stud.entity.TrainDispatchClock;
import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.logic.TrainDispatchRecord;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * The TrainDispatchAppUi class represents the user interface of the application.
 */

public class TrainDispatchAppUi {
  private TrainDispatchRecord trainDispatchRecord;

  private TrainDispatchClock trainDispatchClock;


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
    this.trainDispatchClock = new TrainDispatchClock();
  }

  /**
   * Starts the application. This is the main entry point where the program execution begins.
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
   * If the user enters an invalid menu choice(not a positive int), the method will return -1.</p>
   *
   * @return the menu choice by the user, or -1 if the user entered an invalid menu choice.
   */
  private int getUsersMenuChoice() {
    int selectedMenu;
    Scanner inputScanner = new Scanner(System.in);
    System.out.println("Please enter your choice between 1 and: " + MAX_MENU_CHOICE + ": ");
    if (inputScanner.hasNextInt()) {
      selectedMenu = inputScanner.nextInt();
    } else {
      selectedMenu = -1;
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
   * and {@code true} will be returned.</p>
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
   */

  private void addDelay() {
    final Scanner inputScanner = new Scanner(System.in);
    int searchingTrainNumber = this.correctTrainNumberError();

    System.out.println("Please enter delay in hh:mm: ");
    LocalTime parsedDelay = null;
    boolean validDelay = false;
    while (!validDelay) {
      String delay = inputScanner.nextLine();
      try {
        parsedDelay = LocalTime.parse(delay);
        validDelay = true;
      } catch (DateTimeParseException e) {
        System.out.println("Invalid delay. Please try again.");
      }
    }

    TrainDeparture trainDeparture = this.trainDispatchRecord
        .findTrainDepartureByTrainNumber(searchingTrainNumber);
    if (trainDeparture == null) {
      System.out.println("No train departures found with train number: " + searchingTrainNumber);
    } else {
      trainDeparture.setDelay(parsedDelay);

    }

  }

  /**
   * assign track to a train departure by searching for the train number.
   */

  private void addTrack() {
    final Scanner inputScanner = new Scanner(System.in);
    int searchInTrainNumber = this.correctTrainNumberError();

    int track = this.correctTrack();

    TrainDeparture trainDeparture = this.trainDispatchRecord
        .findTrainDepartureByTrainNumber(searchInTrainNumber);
    if (trainDeparture != null) {
      trainDeparture.setTrack(track);
    } else {
      System.out.println("No train departures found with train number: " + searchInTrainNumber);
    }
  }

  /**
   * Handles the clock update, and throws an exeption if the time is invalid.
   * <p> If the time is invalid, a message will be printed to the console.</p>
   *
   * @throws IllegalArgumentException if the time is invalid.
   */

  private void handleClockUpdate() {
    Scanner scanner = new Scanner(System.in);
    LocalTime parsedTime = null;
    String newTime;
    boolean validTime = false;
    while (!validTime) {
      System.out.println("Please enter the new time in hh:mm: ");
      newTime = scanner.nextLine();

      try {
        parsedTime = LocalTime.parse(newTime);
        validTime = true;
      } catch (DateTimeParseException e) {
        System.out.println("Invalid time. Please try again.");
      }
    }
    this.updateClock(parsedTime);
    System.out.println("The clock has been updated to: " + parsedTime);

  }

  /**
   * Updates the clock to the given time taking the removedTrainDepartures method inta account.
   *
   * @param newTime the new time the clock is updated to.
   */
  private void updateClock(LocalTime newTime) {
    this.trainDispatchClock.setCurrentTime(newTime);
    this.removeDepartedTrainDepartures();
  }

  /**
   * Removes a train departure from the register.
   * <p> If no train departures are found, a message will be printed to the console.</p>
   *
   * <p> If train departures are found, the information about the train departures
   * will be printed to the console.</p>
   * >p></p>
   */
  private void removeDepartedTrainDepartures() {
    LocalTime currentTime = this.trainDispatchClock.getCurrentTime();
    int removedDepartures = trainDispatchRecord.removeTrainDeparturesFromBefore(currentTime);
    if (removedDepartures == 0) {
      System.out.println("No train departures removed");
    } else {
      System.out.println("Removed " + removedDepartures + " train departures");
    }
  }


  /**
   * Prints the information about a train departure.
   *
   * @param trainDeparture the train departure to print information about.
   */
  private void printTrainDepartureInfo(TrainDeparture trainDeparture) {
    System.out.println("Scheduled time of departure: " + trainDeparture.getDepartureTime());
    try {
      System.out.println("Actual time of departure: "
          + trainDeparture.getDepartureTime().plusHours(trainDeparture.getDelay().getHour())
          .plusMinutes(trainDeparture
              .getDelay().getMinute()));
    } catch (Exception e) {
    }
    System.out.println("Line of train: " + trainDeparture.getLine());
    System.out.println("Train number: " + trainDeparture.getTrainNumber());
    System.out.println("Destination: " + trainDeparture.getDestination().toUpperCase());
    if (trainDeparture.getDelay() != (LocalTime.parse("00:00"))) {
      System.out.println("Delay: " + trainDeparture.getDelay());
    }
    if (trainDeparture.getTrack() != -1) {
      System.out.println("Track of train:" + trainDeparture.getTrack());
    }

  }

  /**
   * Searches for train departures by destination.
   * <p> If no train departures are found, a message will be printed to the user.</p>
   * <p> If train departures are found,
   * the information about the train departures will be printed.</p>
   */

  private void searchByDestination() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter a destination you want to search for: ");
    String searchDestination = scanner.nextLine().trim().toUpperCase();

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
   */
  private void searchByTrainNumber() {
    int searchTrainNumber = this.correctTrainNumberError();

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
   */
  private void listAllTrainDeparturesSortedByDepartureTime() {
    for (TrainDeparture trainDeparture : this.trainDispatchRecord
        .getTrainDeparturesSortedByDepartureTime()) {
      this.printTrainDepartureInfo(trainDeparture);

    }
  }


  /**
   * Adds a train departure to the register by asking the user for the information about the
   * train departures.
   * //todo: add validation of input
   */
  private void addNewTrainDeparture() {
    final Scanner inputScanner = new Scanner(System.in);
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

    System.out.println("Please enter line of train: ");
    final String line = inputScanner.nextLine();


    int trainNumber = this.correctTrainNumberError();
    while (this.trainDispatchRecord.findTrainDepartureByTrainNumber(trainNumber) != null) {
      System.out.println("Train number already in use. Please enter new.");
      trainNumber = this.correctTrainNumberError();

    } //Used copilot to finish up my code

    System.out.println("Please enter the destination: ");
    final String destination = inputScanner.nextLine();

    boolean validDelay = false;
    String delay = null;
    while (!validDelay) {
      System.out.println("Please enter delay in hh:mm: ");
      delay = inputScanner.nextLine();
      try {
        LocalTime.parse(delay);
        validDelay = true;
      } catch (DateTimeParseException e) {
        System.out.println("Invalid delay. Please try again.");
      }
    }

    int track = this.correctTrack();


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
   * <p> If the user enters an invalid train number, a message will be printed to the console.</p>
   */

  private int correctTrainNumberError() {
    final Scanner inputScanner = new Scanner(System.in);
    int trainNumber = 0;
    boolean validTrainNumber = false;

    while (!validTrainNumber) {
      System.out.println("Please enter the train number: ");
      if (inputScanner.hasNextInt()) {

        int giventrainNumber = inputScanner.nextInt();
        validTrainNumber = true;

        if (giventrainNumber > 0) {
          trainNumber = giventrainNumber;
        } else {
          System.out.println("Invalid train number. Please try again.");
        }
      } else {
        System.out.println("Invalid train number. Please try again.");
        inputScanner.nextLine();
      }

    }
    return trainNumber;
  }

  /**
   * A method which handles invalid input for track.
   * <p> If the user enters an invalid track, a message will be printed to the console.</p>
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


}





