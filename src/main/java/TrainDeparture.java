import java.time.LocalTime;
public class TrainDeparture {
    private LocalTime departureTime;
    private String line;
    private String trainNumber;
    private String destination;
    private LocalTime delay;
    private int track;

    public TrainDeparture(LocalTime departureTime, String line, String trainNumber, String destination,LocalTime delay,  int track) {
        this.departureTime = departureTime;
        this.line = line;
        this.trainNumber = trainNumber;
        this.destination = destination;
        this.delay = delay;
        this.track = track;
    }



}

