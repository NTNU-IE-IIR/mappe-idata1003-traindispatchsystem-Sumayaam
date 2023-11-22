package edu.ntnu.stud;

import edu.ntnu.stud.ui.TrainDispatchAppUi;

/**
 *
 *
 */

public class TrainDispatchApp {
  /**
   *
   * @param args
   */
  public static void main(String[] args) {
    try {
      TrainDispatchAppUi trainDispatchAppUi = new TrainDispatchAppUi();
      trainDispatchAppUi.init();
      trainDispatchAppUi.start();
    } catch (IllegalArgumentException e) {

    }


  }
}
