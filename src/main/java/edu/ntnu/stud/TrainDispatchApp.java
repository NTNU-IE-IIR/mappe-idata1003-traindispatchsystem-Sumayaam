package edu.ntnu.stud;

import edu.ntnu.stud.ui.TrainDispatchAppUi;

/**
 * //TODO: Add description.
 */

public class TrainDispatchApp {
  /**
   * Represents the main application for the Train Dispatch System.
   * The main method is the entry point of the application.
   *
   * @author Sumaya Mohammud
   * version 1.0.0.
   */
  public static void main(String[] args) {
    try {
      TrainDispatchAppUi trainDispatchAppUi = new TrainDispatchAppUi();
      trainDispatchAppUi.init();
      trainDispatchAppUi.start();
    } catch (IllegalArgumentException e) {
      System.out.println("Something went wrong during initialization of the app.");
      //Used Copilot to generate this code.


    }


  }
}
