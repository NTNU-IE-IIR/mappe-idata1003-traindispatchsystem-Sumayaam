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
 * <ul>
 *   <li> Add a train departure where each has a unique train number,and can only be added once</li>
 *   <li> Find a train departure by an existing train number</li>
 *   <li> Find a train departure by an existing  destination</li>
 *   <li> Remove train departures from <b>before</b> a given time</li>
 *   <li> Get a list of train departures sorted by departure time</li>
 *   </ul>
 */
public class TrainDispatchRecord {
  private final HashMap<Integer, TrainDeparture> trainDepartures;
  private final ArrayList<Integer> usedTrainNumbers;


  /**
   * Creates an instance of TrainDispatchRecord.
   */
  public TrainDispatchRecord() {
    this.trainDepartures = new HashMap<>();
    this.usedTrainNumbers = new ArrayList<>();
  }

  /**
   * Adds a train departure to the record.
   * <p>
   * This method adds a {@code TrainDeparture} to the record.
   * if a train departure with the same train number already exists in the record,
   * or the train number has been used by another train earlier in the day:
   * the method will return {@code false} and will not add the train number.
   * if the train number does not exist in the record, the method will then return {@code true}.
   * </p>
   *
   * @param trainDeparture the train departure to be added.
   * @return {@code true} if the train departure was added,
   *         {@code false} if the train departure was not added.
   */
  public boolean addTrainDeparture(TrainDeparture trainDeparture) {
    if (trainDeparture == null) {
      return false;

    }
    int trainNumber = trainDeparture.getTrainNumber();

    if (this.trainDepartures.containsKey(trainNumber)
        || this.usedTrainNumbers.contains(trainNumber)) {
      return false; // Train number already exits.
    }
    this.trainDepartures.put(trainNumber, trainDeparture);
    this.usedTrainNumbers.add(trainNumber); // Adds train number to the used train numbers list.

    return true;

  }

  /**
   * Returns the ArrayList of used train numbers in the record,
   * so it has better availability for usage in other classes.
   *
   * @return the ArrayList of used train numbers.
   */
  public ArrayList<Integer> getUsedTrainNumbers() {
    return this.usedTrainNumbers;
  }

  /**
   * Returns the number train departures in the record.
   *
   * @return number of train departures.
   */
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
   * being returned. We are using HashMap1s lack of awareness to our advantage,
   * because we are using the iterator to remove the entries from the hashmap,
   * and the first match is returned. This leads to a better performance in an unsorted structure,
   * because we are not iterating over the entirety of the HasHMap
   *
   * @param destination of the train departure to search for.
   * @return the train departure found with the given destination.
   */
  public TrainDeparture findTrainDepartureByDestination(String destination) {
    TrainDeparture foundTrainDeparture = null; //Initializing the found train departure to null.

    Iterator<TrainDeparture> it = this.trainDepartures.values().iterator();
    //Iterator for iterating over the HashMap values.

    while ((foundTrainDeparture == null) && it.hasNext()) {
      TrainDeparture trainDeparture = it.next();
      if (trainDeparture.getDestination().equalsIgnoreCase(destination)) {
        foundTrainDeparture = trainDeparture;
      } // Searching for the train departure with the final destination.
    }
    return foundTrainDeparture;
    // Returns the train departure with the final destination, or otherwise null.
  }

  /**
   * Remove Departure from the record that are scheduled to depart before the given time,
   * taking the delay into account.
   * <p> This method uses an iterator to iterate over the hashmap entries.
   * It is more efficient because we
   * are using the iterator to remove the entries from the hashmap,
   * instead of using the remove method on the hashmap.
   * This approach is deemed more efficient because
   * the iterator is aware of the changes made to the hashmap,
   * and can remove the entries without cause an error.
   * It is also more flexible than a for each or a while loop and calling
   * the remove method on the hashmap.
   * This can also result in errors, because of HashMap's lack of awareness.
   * </p> This operation will make sure that the train departures are removed from the
   * record smoothly.
   *
   * @param removeTime the time before which all departures should be removed.
   */

  public int removeTrainDeparturesFromBefore(LocalTime removeTime) {
    Iterator<Map.Entry<Integer, TrainDeparture>> it = this.trainDepartures.entrySet().iterator();
    int removedCount = 0;
    // Iterating over the hashmap entries.

    while (it.hasNext()) {
      HashMap.Entry<Integer, TrainDeparture> entry = it.next();
      TrainDeparture trainDeparture = entry.getValue();
      // Gets the train departure value from the entry.
      LocalTime delay = trainDeparture.getDelay();
      LocalTime newDepartureTime = trainDeparture.getDepartureTime().plusHours(delay.getHour())
          .plusMinutes(delay.getMinute());
      // Calculates the final departure time by adding the delay to the scheduled departure time.

      if (newDepartureTime.isBefore(removeTime)) {
        it.remove();
        removedCount++;
        //Removes the train departure from the record if they are before the final departure time,
        // And adds to the removed count
      }
    }
    return removedCount;
    // Returns how many train departures where removed.
  }


  /**
   * Returns the number of train departures as a sorted list depending on departure Time,
   * by using Lambda expressions. and returning the sorted list.
   *
   * @return a sorted list of train departures by departure time.
   */

  public ArrayList<TrainDeparture> getTrainDeparturesSortedByDepartureTime() {
    ArrayList<TrainDeparture> sortedTrainDepartureList = new ArrayList<>(
        this.trainDepartures.values()); // Initializing the train departure as a sorted list.
    // Compares the departure times of the train departures using a lambda expression
    // and sorts the list.
    // The 'sort' method takes a Comparator, which is here implemented using a lambda expression.
    // This lambda expression compares the departure time of two train departures
    // ('trainDeparture1' and 'trainDeparture2').
    // The result of 'compareTo' determines the order in the sorted list.

    sortedTrainDepartureList.sort((trainDeparture1, trainDeparture2) ->
        trainDeparture1.getDepartureTime()
            .compareTo(
                trainDeparture2.getDepartureTime()));
    return sortedTrainDepartureList;
  }


}


