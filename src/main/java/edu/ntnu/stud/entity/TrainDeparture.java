package edu.ntnu.stud.entity;

import java.time.LocalTime;

/**
 * Represents a class that contain information about a train departure.
 * Some of the information can be changed,
 * which means this class contains both immutable and mutable fields
 * to represent the characteristics of a train departure.
 * This class provides methods to both access and update the information to mutable fields,
 * and retrieve info from immutable fields.
 *
 *
 *
 * <p>It contains the following attributes:
 *
 * <ul>
 *   <li><b>departureTime </b> (LocalTime): The scheduled departure time of the train.
 *   Has to be valid LocalTime in hh:mm format, because it represents time.
 *   This field is immutable </li>
 *   <li><b>line</b> (String) The train line,
 *   which is a specified text that cannot be null or empty line.
 *   This field is immutable.</li>
 *   <li><b>trainNumber</b> (int) The train number
 *   which is unique for each train and must be positive
 *   Int is used because it is easier to prevent these errors.</li>
 *   <li><b>destination</b> (String) The destination the train is headed to.
 *   This field is immutable.</li>
 *   <li><b>delay</b>(LocalTime) The time of delay of the train and handles time. (hh.mm)
 *   if there is no delay then it is set to 00.00. This field is mutable</li>
 *   <li><b>track</b> (int) the track number of the train return -1 if no track is assigned,
 *   which is why int was the best choice because of this principle. This field is mutable.</li>
 *   </ul>
 *
 *   <p>The fields have the following data types:
 *     <ul>
 *       <li>departureTime: LocalTime, because it represents time,
 *       and final since it wont change</li>
 *       <li>line: String, because it represents a text</li>
 *       <li>trainNumber: int, because it represents a number</li>
 *       <li>destination: String, because it represents a text</li>
 *       <li>delay: LocalTime, because it represents time</li>
 *       <li>track: int, because it represents a number</li>
 *    </ul>
 *
 * <p>Mutable fields:
 * <ul>
 *    <li>delay: Can be updated to show the current departure time status of the train.</li>
 *    <li>track: Can be updated to update an existing track or assign a new track</li>
 *    </ul>
 * <p> Immutable fields:</p>
 * <ul>
 *   <li>departureTime: Cannot be updated because it is the <b>scheduled departure</b>
 *   time of the train.</li>
 *   <li>line: Cannot be updated because it is the train line.</li>
 *   <li>trainNumber: Cannot be updated because it is the train number.</li>
 *   <li>destination: Cannot be updated because it is the final destination of the train.</li>
 *   </ul>
 *
 * @author Sumaya
 * @version 2023-10-20
 */

public class TrainDeparture {
  private LocalTime departureTime;
  private String line;
  private int trainNumber;
  private String destination;
  private LocalTime delay;
  private int track;

  /**
   * Creates a train departure Object with specified attributes.
   *
   * @param departureTime The departure time of the train(has to be valid LocalTime).
   * @param line          The train line.(cannot be null or empty line).
   * @param trainNumber   The train number(unique for each train and must be positive).
   * @param destination   The train destination.
   * @param delay         The delay of the train (hh.mm) if no delay then 00.00.
   * @param track         the track number of the train(return -1 if no track is assigned).
   */
  public TrainDeparture(LocalTime departureTime, String line, int trainNumber,
                        String destination, LocalTime delay, int track) {
    this.setDepartureTime(departureTime);
    this.setLine(line);
    this.setTrainNumber(trainNumber);
    this.setDestination(destination);
    this.setDelay(delay); // The delay of the train (hh.mm) (00.00, if no delay).
    this.setTrack(track); // the track number of the train (-1 if no track is assigned).
  }


  /**
   * Returns the departure time.
   *
   * @return departure time.
   */
  public LocalTime getDepartureTime() {
    return departureTime;

  }

  /**
   * Returns the line of the train.
   *
   * @return line of the train.
   */

  public String getLine() {
    return line;
  }

  /**
   * Returns the train number of the train.
   *
   * @return train number of the train.
   */

  public int getTrainNumber() {
    return trainNumber;
  }

  /**
   * Returns the train destination.
   *
   * @return destination of the train.
   */

  public String getDestination() {
    return destination;
  }

  /**
   * Returns the delay of the train.
   *
   * @return delay of the train.
   */
  public LocalTime getDelay() {
    return delay;
  }

  /**
   * Returns the Track of the train.
   *
   * @return track of the train.
   */

  public int getTrack() {
    return track;
  }

  /**
   * Sets the departure time of the train.
   *
   * @param departureTime to be set.
   */

  private void setDepartureTime(LocalTime departureTime) {

    this.departureTime = departureTime;
  }

  /**
   * Sets the line of the train.
   *
   * @param line to be set.
   */
  private void setLine(String line) {
    if ((line == null) || line.isBlank()) {
      this.line = "INVALID LINE";
    } else {
      this.line = line;
    }
  }

  /**
   * Sets the train number of the train
   * If the train number is negative, the train number is set to 0.
   *
   * @param trainNumber to be set. Must be a positive number.
   */
  private void setTrainNumber(int trainNumber) {
    if (trainNumber > 0) {
      this.trainNumber = trainNumber;
    }
  }

  /**
   * Sets the destination of the train.
   * If the destination is null or empty, the destination is set to "INVALID DESTINATION"
   *
   * @param destination to be set. Must contain a text for final destination
   */
  private void setDestination(String destination) {
    if ((destination == null) || destination.isBlank()) {
      this.destination = "INVALID DESTINATION";
    } else {
      this.destination = destination;
    }
  }

  /**
   * Sets the delay of the train.
   * If the delay is null, the delay is set to (00.00).
   *
   * @param delay the amount of delay.
   */
  public void setDelay(LocalTime delay) {
    if (delay == null) {
      this.delay = LocalTime.of(0, 0);
    } else {
      this.delay = delay;
    }
  }


  /**
   * sets the track of the train.
   * If the track is negative, the track is set to 0
   * and if no trick is assigned, the track is set to -1.
   *
   * @param track the track number.
   */

  public void setTrack(int track) {
    if (track > 0) {
      this.track = track;
    } else {
      this.track = -1;
    }
  }

  public int compareTo(TrainDeparture otherTrainDeparture) {
    return this.getDepartureTime().compareTo(otherTrainDeparture.getDepartureTime());
  }
}



