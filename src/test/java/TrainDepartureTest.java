import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TrainDepartureTest {
    @Test //Positive test
    void validTrainDeparture(){
        LocalTime validDeparture = LocalTime.of(12,1);
        String line = "F1";
        String trainNumber = "601";
        String destination = "Helsinki";
        LocalTime delay = LocalTime.of(0,10);
        int track = 61;

        TrainDeparture traindeparture = new TrainDeparture(validDeparture, line, trainNumber, destination, delay, track);

        assertEquals(validDeparture, traindeparture.getDepartureTime());
        assertEquals(line, traindeparture.getLine());
        assertEquals(trainNumber, traindeparture.getTrainNumber());
        assertEquals(destination, traindeparture.getDestination());
        assertEquals(delay, traindeparture.getDelay());
        assertEquals(track, traindeparture.getTrack());
    }

    @Test
    void InCorrectTrainDeparture(){
        LocalTime validDeparture = LocalTime.of(30,0);
        String line = "F_";
        String trainNumber = "601";
        String destination = "Helsinki";
        LocalTime delay = LocalTime.of(0,10);
        int track = 61;
    }

}