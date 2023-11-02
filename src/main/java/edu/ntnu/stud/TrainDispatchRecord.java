package edu.ntnu.stud;

import java.util.HashMap;

/**
 * Represents a record of train departures.
 * <p> The following functionality is implemented.
 * <ul> <li> Add a train departure</li>
 *
 *
 *
 *
 *
 * </ul>
 *
 */
public class TrainDispatchRecord {
  private HashMap<Integer, TrainDeparture> traindepartures;


    /**
     * Creates an instance of TrainDispatchRecord.
     */
  public TrainDispatchRecord() {
      this.traindepartures = new HashMap<>();
  }

    /**
     * Adds a train departure to the record.
     *<p><
     * This method adds a {@code TrainDeparture} to the record.
     * if a train departure with the same train number already exists in the record,
     * the method will return {@code false} and will not add the train number.
     * if the trian number does not exist in the record, the method will then return {@code true}.
     * </p>
     *
     * @param trainDeparture the train departure to be added.
     * @return {@code true} if the train departure was added.
     * @return {@code false} if the train departure was not added.
     */
   public boolean addTrainDeparture(TrainDeparture trainDeparture) {
       int trainNumber = trainDeparture.getTrainNumber();
       if (this.traindepartures.containsKey(trainNumber)) { //if the train number already exists
           return false; // if yes return false
       } else {
           this.traindepartures.put(trainNumber, trainDeparture);
           return true; //if no return true, and add the train departure
       }

      }
    }
}