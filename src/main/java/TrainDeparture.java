import java.time.LocalTime;

/**Represents a Train departure with departure time, line and
 * train number, destination, delay and track.
 *
 * @author Sumaya
 * @version 2023-10-20
 */



// define the Class TrainDeparture
public class TrainDeparture {
  private LocalTime departureTime;
  private String line;
  private String trainNumber;
  private String destination;
  private LocalTime delay;
  private int track;

  /** Creates a trainDeparture Object with specified attributes.
     *
     * @param departureTime  The departure time of the train.
 *
     * @param line           The train line.
 *
     * @param trainNumber    The train number.
 *
     * @param destination    The train destination.
 *
     * @param delay          The delay of the train.
 *
     * @param track          the track number of the train.
 *                       
     */
  public TrainDeparture(LocalTime departureTime, String line, String trainNumber,
                        String destination, LocalTime delay, int track) {

    this.departureTime = departureTime;
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.delay = delay;
    this.track = track;
  }


  //create accsessor methods to get information
  public LocalTime getDepartureTime() {
      
    return departureTime;
  }

  public String getLine() {
    return line;
  }

  public String getTrainNumber() {
    return trainNumber;
  }

  public String getDestination() {
    
    return destination;
  }

  public LocalTime getDelay() {
    return delay;
  }

  public int  getTrack() {
    return track;
  }
}




