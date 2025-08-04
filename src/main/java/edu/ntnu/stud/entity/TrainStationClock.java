package edu.ntnu.stud.entity;

import java.time.LocalTime;

/**
 * The TrainDispatchClock class represents a clock for managing train departures.
 * This clock tracks time starting from 00:00 to 23:59, and allows updates to the current time.
 * Time cannot be set backwards.
 */

public class TrainStationClock {
  private LocalTime currentTime;

  /**
   * Creates an instance of the TrainStationClock,with the current time set to 00:00.
   */

  public TrainStationClock() {
    currentTime = LocalTime.of(0, 0);
  }

  /**
   * Returns the current time as localtime instance.
   *
   * @return current time.
   */

  public LocalTime getCurrentTime() {
    return currentTime;
  }

  /**
   * Sets the current time to the specified time. The new time must be later than the current time,
   * and within 00:00 and 23:59. If not it will throw an IllegalArgumentException.
   *
   * @param time the new time to be set. Must not be earlier than the current time.
   * @throws IllegalArgumentException if the time does not fulfill conditions.
   */

  public void setCurrentTime(LocalTime time) {
    if (time == null) {
      throw new IllegalArgumentException("Time cannot be null");
    }

    if (time.isBefore(currentTime)) {
      throw new IllegalArgumentException(
          "Time cannot be set to an earlier time than the current time!");
    }
    if (time.isAfter(LocalTime.MAX) || time.isBefore(LocalTime.MIDNIGHT)) {
      throw new IllegalArgumentException("Time must be between 00:00 and 23:59");
    }
    this.currentTime = time;
  }

}




