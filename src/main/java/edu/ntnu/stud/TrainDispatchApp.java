package edu.ntnu.stud;

import edu.ntnu.stud.ui.TrainDispatchAppUi;

/**
 * //TODO: Add description.
 */

public class TrainDispatchApp {
  /**
   * //TODO: Add description.
   *
   * @param args
   */
  public static void main(String[] args) {
    try {
      TrainDispatchAppUi trainDispatchAppUi = new TrainDispatchAppUi();
      trainDispatchAppUi.init();
      trainDispatchAppUi.start();
    } catch (IllegalArgumentException e) {
      throw new RuntimeException(e); // todo fix
    }


  }
}
