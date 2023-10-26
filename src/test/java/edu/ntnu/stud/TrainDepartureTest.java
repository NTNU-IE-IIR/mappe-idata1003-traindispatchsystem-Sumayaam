package edu.ntnu.stud;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class TrainDepartureTest {
    private TrainDeparture departure;
    /**
     * Sets up the text fixture.
     *
     * Always called before each test case method.
     */

    @BeforeEach

    public void setUp(){
        departure = new TrainDeparture(LocalTime.of(12, 00),
                "F1",
                "789",
                "Oslo",
                3);

    }

    /**
     * Tears down the text fixture
     *
     * Always called after every test case
     */

    @AfterEach
    public void teardown() {
        departure = null;

    }

    /**
     * Positive test which test valid train departure inputs.
     */
    @Test
    public void testvalidtraindepartureinput() {
        assertEquals(LocalTime.of(12, 0), departure.getDepartureTime());
        assertEquals("F1",departure.getLine());
        assertEquals("789", departure.getTrainNumber());
        assertEquals("Oslo", departure.getDestination());
        assertEquals(3, departure.getTrack());
    }

    /**
     * Positive test for track
     */

    @Test
    public void testValidTrack(){
        departure.setTrack(4);
        assertEquals(4, departure.getTrack());
    }
    @Test

    public void testsInvalidInputForTrack() {
        departure.setTrack(-10);
        assertEquals(-10, departure.getTrack());
    }

}