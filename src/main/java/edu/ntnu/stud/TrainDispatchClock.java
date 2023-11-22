package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * The TrainDispatchClock class represents a clock for managing train departures.
 * This clock tracks time starting from 00:00 to 23:59, and allows updates to the current time.
 * Time cannot be set backwards.
 *
 */

public class TrainDispatchClock {
  private  LocalTime currentTime;

  /**
  * Creates an instance of the TrainDispatchClock.
  */

  public  TrainDispatchClock() {
    currentTime = LocalTime.of(0, 0);
  }
  /**
   * Returns the current time as localtime instance.
   *
   * @return current time.
   */

  public  LocalTime getCurrentTime() {
    return currentTime;
  }

  /**
   * Sets the current time to the specified time.The new time must be later than the current time,
   * and within 00:00 and 23:59. If not the time will be updated to 00:00.
   *
   * @param time the new time to be set. Must not be earlier than the current time.
   */

  public void setCurrentTime(LocalTime time) {
    //TODO- USE EXEPTIONS
    if (time == null || time.isBefore(LocalTime.of(0, 0)) || time.isAfter(LocalTime.of(23, 59))
            || time.isBefore(this.currentTime)) {
      this.currentTime = LocalTime.of(0, 0);
    } else {
      this.currentTime = time;
    }
  }
}

