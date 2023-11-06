package edu.ntnu.stud;

import java.time.LocalTime;

/** Represents a class that contain information about Train Departures.
 *
 *
 * @author Sumaya
 * @version 2023-10-20
 */

public class TrainDeparture {
  private  LocalTime departureTime;
  private  String  line;
  private int trainNumber;
  private String destination;
  private  LocalTime delay;
  private int track;

  /** Creates a train departure Object with specified attributes.
   *
   * @param departureTime  The departure time of the train(has to be valid LocalTime).
   * @param line           The train line.(cannot be null or empty line).
   * @param trainNumber    The train number(uniqye for each day and must be positive).
   * @param destination    The train destination.
   * @param delay          The delay of the train (hh.mm) (00.00, if no delay).
   * @param track          the track number of the train(empty line if no track is assigned).
   *
   */
  public TrainDeparture(LocalTime departureTime, String line, int trainNumber,
                              String destination, LocalTime delay, int track) {
    this.setDepartureTime(departureTime);
    this.setLine(line);
    this.setTrainNumber(trainNumber);
    this.setDestination(destination);
    this.setDelay(delay); // The delay of the train (hh.mm) (00.00, if no delay).
    this.setTrack(track); // the track number of the train(empty line if no track is assigned).
        }


  /**
  * Returns the departure time.
  *
  * @return departure time.
  *
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
  * Returns the delay of the train
  *
  * @return delay of the train.
  */
  public LocalTime getDelay() {
    return delay;
   }

  /**
  * Returns the Track of the train
  *
  * @return track of the train.
  */

  public int getTrack() {
    return track;
    }
  /**
   * Sets the departure time of the train
   * @param departureTime to be set.
   */

  private void setDepartureTime(LocalTime departureTime) {

    this.departureTime = departureTime;
  }

  /**
   * Sets the line of the train
   *
   * @param line to be set.
   *
   */
  private void setLine(String line) {
    this.line = line;
  }

  /**
   * Sets the train number of the train
   * If the train number is negative, the train number is set to 0.
   *
   * @param trainNumber to be set. Must be a positive number.
   */
  private void setTrainNumber(int trainNumber) {
    if (trainNumber >0)
      this.trainNumber = trainNumber;
  }

  /**
   *Sets the destination of the train.
   * If the destination is null or empty, the destination is set to "INVALID DESTINATION"
   *
   * @param destination to be set. Must contain a text for final destination
   */
  private void setDestination(String destination) {
    if ((destination == null) || destination.isBlank()) {
      this.destination = "INVALID DESTINATION";
    } else
    this.destination = destination;
  }


  /**
 * Sets the delay of the train.
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
   * If the track is negative, the track is set to 0.
  *
  * @param track the track number.
  */

  public void setTrack(int track) {
    if (track > 0) {
      this.track = track;
    }

  }

}



