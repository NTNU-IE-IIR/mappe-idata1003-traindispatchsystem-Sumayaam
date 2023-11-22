package edu.ntnu.stud.logic;

import edu.ntnu.stud.entity.TrainDeparture;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Represents a record of train departures.
 *
 * <p>The following functionality is implemented.
 *
 * <ul> <li> Add a train departure</li>
 *
 *
 *
 *
 *
 * </ul>
 */
public class TrainDispatchRecord {
  private HashMap<Integer, TrainDeparture> trainDepartures;


  /**
   * Creates an instance of TrainDispatchRecord.
   */
  public TrainDispatchRecord() {
    this.trainDepartures = new HashMap<>();
  }

  /**
   * Adds a train departure to the record.
   * <p><
   * This method adds a {@code TrainDeparture} to the record.
   * if a train departure with the same train number already exists in the record,
   * the method will return {@code false} and will not add the train number.
   * if the train number does not exist in the record, the method will then return {@code true}.
   * </p>
   *
   * @param trainDeparture the train departure to be added.
   * @return {@code true} if the train departure was added,
   * and {@code false} if the train departure was not added.
   */
  public boolean addTrainDeparture(TrainDeparture trainDeparture) {
    if (trainDeparture == null || this.trainDepartures.containsKey(
        trainDeparture.getTrainNumber())) {
      return false;
    }
    this.trainDepartures.put(trainDeparture.getTrainNumber(), trainDeparture);
    return true;
  }

  public int getNumberOfTrainDepartures() {
    return this.trainDepartures.size();
  }

  /**
   * Returns the train departure with a given train number.
   *
   * @param trainNumber the train number of the train departure to return.
   *                    if no train departure found null is returned.
   * @return the train departure with a given train number.
   */

  public TrainDeparture findTrainDepartureByTrainNumber(int trainNumber) {
    return this.trainDepartures.get(trainNumber);
  }


  /**
   * Searches the record for a train departure with the given destination.
   * If no train departure matches the destination, null is returned.
   *
   * <p>in this method we use an iterator to iterate over the values of the hashmap.
   * resulting in the first train departure in the record with a matching destination
   * being returned.
   *
   * @param destination of the train departure to search for.
   * @return the train departure found with the given destination.
   */
  public TrainDeparture findTrainDepartureByDestination(String destination) {
    TrainDeparture foundTrainDeparture = null;

    Iterator<TrainDeparture> it = this.trainDepartures.values().iterator();

    while ((foundTrainDeparture == null) && it.hasNext()) {
      TrainDeparture trainDeparture = it.next();
      if (trainDeparture.getDestination().equals(destination)) {
        foundTrainDeparture = trainDeparture;
      }
    }
    return foundTrainDeparture;
  }

  /**
   * Remove Departure from the record that are scheduled to depart before the given time,
   * taking the delay into account.
   *
   * @param removeTime the time before which all departures should be removed.
   */


  public int removeTrainDeparturesFromBefore(LocalTime removeTime) {
    Iterator<Map.Entry<Integer, TrainDeparture>> it = this.trainDepartures.entrySet().iterator();
    int removedCount = 0;

    while (it.hasNext()) {
      HashMap.Entry<Integer, TrainDeparture> entry = it.next();
      TrainDeparture trainDeparture = entry.getValue();

      LocalTime delay = trainDeparture.getDelay();
      LocalTime newDepartureTime = trainDeparture.getDepartureTime().plusHours(delay.getHour())
          .plusMinutes(delay.getMinute());

      if (newDepartureTime.isBefore(removeTime)) {
        it.remove();
        removedCount++;
      }
    }
    return removedCount;
  }


  /**
   * Returns the number of train departures as a sorted list depending on departure Time.
   *
   * @return a sorted list of train departures by departure time.
   */

  public ArrayList<TrainDeparture> getTrainDeparturesSortedByDepartureTime() {
    ArrayList<TrainDeparture> sortedTrainDepartureList = new ArrayList<>(
        this.trainDepartures.values());
    sortedTrainDepartureList.sort((trainDeparture1, trainDeparture2) ->
        trainDeparture1.getDepartureTime()
            .compareTo(trainDeparture2.getDepartureTime()));
    return sortedTrainDepartureList;
  }

  /**
   * Return an iterator for the record.
   *
   * @return iterator for the record.
   */
  public Iterator<TrainDeparture> iterator() {
    return this.trainDepartures.values().iterator();
  }
}


