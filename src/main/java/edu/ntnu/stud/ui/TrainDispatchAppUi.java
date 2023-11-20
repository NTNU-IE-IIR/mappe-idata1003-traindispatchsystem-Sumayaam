package edu.ntnu.stud.ui;


import edu.ntnu.stud.TrainDispatchClock;
import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.logic.TrainDispatchRecord;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Scanner;

/**
 *The TrainDispatchAppUi class represents the user interface of the application.
 *
 */

public class TrainDispatchAppUi {
  private TrainDispatchRecord trainDispatchRecord;

  private TrainDispatchClock trainDispatchClock;

  // A String constant holding the current version of the application.
  private static final String version = "v0.1-SNAPSHOT";


  /**
     * Creates an instance of TrainDispatchApp.
     */
  private static final int LIST_ALL_TRAIN_DEPARTURES_BY_DEPARTURE_TIME  = 1;
  private static final int ADD_NEW_TRAIN_DEPARTURE = 2;
  private static final int ASSIGN_TRACK = 3;

  private static final int ADD_DELAY = 4;

  private static final int SEARCH_BY_TRAIN_NUMBER = 5;
  private static final int SEARCH_BY_DESTINATION = 6;

  private static final int UPDATE_CLOCK = 7;

  private static final int REMOVE_DEPARTED_TRAIN_DEPARTURES = 8;
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
     *Prints the welcome screen to the user.
     */
  private void printWelcomeScreen() {
    System.out.println("*****  Train Dispatch Record" + version + " *****");
    System.out.println("Welcome to this application!");
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
    System.out.println("8. Remove departed train departures.");
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
        this.assignTrack();
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

      case REMOVE_DEPARTED_TRAIN_DEPARTURES:
        this.removeDepartedTrainDepartures();
        break;


      case EXIT:
        result = false;
        break;

      default:
        System.out.println("\n Please enter a number between 1 and" + MAX_MENU_CHOICE);
        System.out.println("Invalid menu choice. Please try again.");
        break;
    }
    return result;
  }

  /**
   * Adding delay to a train departyre by searching for the train number.
   */

  private void addDelay() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter the train number you want to add delay to: ");
    int searchTrainNumber = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Please enter the delay in hh:mm: ");
    String delay = scanner.nextLine();

    TrainDeparture trainDeparture = this.trainDispatchRecord
            .findTrainDepartureByTrainNumber(searchTrainNumber);
    if (trainDeparture != null) {
      trainDeparture.setDelay(LocalTime.parse(delay));
    } else {
      System.out.println("No train departures found with train number: " + searchTrainNumber);

    }

  }

  /**
    * assign track to a train departure by searching for the train number.
    */

  private void assignTrack() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter the train number you want to assign a track to: ");
    int searchTrainNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Please enter the track you want to assign: ");
        int track = scanner.nextInt();
        scanner.nextLine();

        TrainDeparture trainDeparture = this.trainDispatchRecord.
                findTrainDepartureByTrainNumber(searchTrainNumber);
        if (trainDeparture != null) {
            trainDeparture.setTrack(track);
        } else {
            System.out.println("No train departures found with train number: " + searchTrainNumber);
        }
  }

  /**
    * Handles the clock uopdate
    *
    */

  private void handleClockUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the new time in hh:mm: ");
        String newTime = scanner.nextLine();
        this.updateClock(LocalTime.parse(newTime));
  }

    /**
     * Removes a train departure from the register.
     */
    private void removeDepartedTrainDepartures() {
        LocalTime currentTime = trainDispatchClock.getCurrentTime();
        int removedDepartures = trainDispatchRecord.removeTrainDeparturesFromBefore(currentTime);
        this.trainDispatchRecord.removeTrainDeparturesFromBefore(currentTime);
    }

    /**
     * Updates the clock to the given time.
     * @param newTime
     */
    private void updateClock(LocalTime newTime) {
        this.trainDispatchClock.setCurrentTime(newTime);
        this.removeDepartedTrainDepartures();
  }


    /**
     * Prints the information about a train departure.
     * @param trainDeparture the train departure to print information about.
     */
  private void printTrainDepartureInfo (TrainDeparture trainDeparture) {
    System.out.println("Scheduled time of departure: " + trainDeparture.getDepartureTime());
        System.out.println("Line of train: " + trainDeparture.getLine());
        System.out.println("Train number: " + trainDeparture.getTrainNumber());
        System.out.println("Destination: " + trainDeparture.getDestination());
        System.out.println("Delay: " + trainDeparture.getDelay());
        System.out.println("Track of train:" + trainDeparture.getTrack());

    }

    /**
     *
     * Searches for train departures by destination.
     * <p> If no train departures are found, a message will be printed to the console.</p>
     * <p> If train departures are found, the information about the train departures will be printed to the console.</p>
     */

  private void searchByDestination() {
     Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the destination you want to search for: ");
        String searchDestination = scanner.nextLine();
    TrainDeparture trainDeparture = this.trainDispatchRecord.findTrainDepartureByDestination(searchDestination);
        if (trainDeparture == null) {
            System.out.println("No train departures found with destination: " + searchDestination);
        } else {
            System.out.println("Train departures found with destination: " + searchDestination);
                this.printTrainDepartureInfo(trainDeparture);
            }
        }

    /**
     *
     * Searches for train departures by train number.
     * <p>if no train departures are found, a message will be printed to the console.</p>
     * <p>if train departures are found, the information about the train departures will be printed to the console.</p>
     */
    private void searchByTrainNumber() {
      Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the train number you want to search for: ");
        int searchTrainNumber = scanner.nextInt();

        scanner.nextLine();

        TrainDeparture trainDeparture = this.trainDispatchRecord.findTrainDepartureByTrainNumber(searchTrainNumber);
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
      for (TrainDeparture trainDeparture : this.trainDispatchRecord.getTrainDeparturesSortedByDepartureTime()) {
          this.printTrainDepartureInfo(trainDeparture);
      }
  }


  /**
   * Prints all train departures in the register.
   */
  private void printAllTrainDepartures() {
    Iterator<TrainDeparture> it = this.trainDispatchRecord.iterator();
    while(it.hasNext()) {
      TrainDeparture trainDeparture = it.next();
      printTrainDepartureInfo(trainDeparture);
            }
 }

    /**
     * Adds a train departure to the register by asking the user for the information about the train departure.
     */
    private void addNewTrainDeparture() {
          final Scanner inputScanner = new Scanner(System.in);
            System.out.println("Please enter the scheduled time of departure in hh:mm: ");
            final String departureTime = inputScanner.nextLine();

            System.out.println("Please enter line of train: ");
            final String line = inputScanner.nextLine();

            System.out.println("Please enter train number: ");
        System.out.println("Please enter 3-digit train number: ");
        int trainNumber = 0;
        boolean validTrainNumber = false;
        while (!validTrainNumber) {
            if (inputScanner.hasNextInt()) {
                trainNumber = inputScanner.nextInt();
                if (Integer.toString(trainNumber).length() == 3) {
                    validTrainNumber = true;
                } else {
                    System.out.println("Invalid train number. Please enter a 3-digit train number :)");
                    inputScanner.nextLine();
                }
            } else {
                System.out.println("Invalid train number. Please try again.");
                inputScanner.nextLine();
            }
        }
            inputScanner.nextLine();
            System.out.println("Please enter destination: ");
            final String destination = inputScanner.nextLine();

            System.out.println("Please enter delay in hh:mm: ");
            final String delay = inputScanner.nextLine();

            System.out.println("Please enter track: ");
            int track = 0;
            boolean validTrack = false;
            while (!validTrack) {
                if (inputScanner.hasNextInt()) {
                    track = inputScanner.nextInt();
                    validTrack = true;
                } else{
                    System.out.println("Invalid track. Please try again.");
                    inputScanner.nextLine();
                }
            }
            TrainDeparture trainDepartureToAdd = new TrainDeparture(LocalTime.parse(departureTime),
                    line,
                    trainNumber,
                    destination,
                    LocalTime.parse(delay),
                    track);
         boolean isAdded =   this.trainDispatchRecord.addTrainDeparture(trainDepartureToAdd);
            if (isAdded) {
                System.out.println("Train departure added successfully!");
            } else {
                System.out.println( " A train departure with the same train number already exists.");
            }
     }
     }


