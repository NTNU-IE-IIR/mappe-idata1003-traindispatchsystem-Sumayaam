import java.time.LocalTime;
// define the Class TrainDeparture
public class TrainDeparture {
    private LocalTime departureTime;
    private String line;
    private String trainNumber;
    private String destination;
    private LocalTime delay;
    private int track;
//Define the constructor
    public TrainDeparture(LocalTime departureTime, String line, String trainNumber, String destination, LocalTime delay,
                          int track)
    {
        this.departureTime = departureTime;
        this.line = line;
        this.trainNumber = trainNumber;
        this.destination = destination;
        this.delay = delay;
        this.track = track;
    }
// create accsessor methods to get information
    public LocalTime getDepartureTime()
    {
        return departureTime;
    }

    public String getLine()
    {
        return line;
    }

    public String getTrainNumber()
    {
        return trainNumber;
    }

    public String getDestination()
    {
        return destination;
    }

    public LocalTime getDelay()
    {
        return delay;
    }

    public int getTrack() {
        return track;
    }
}




