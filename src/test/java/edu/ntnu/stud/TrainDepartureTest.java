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
        departure = new TrainDeparture(LocalTime.of(12, 00), "F1", "234","Oslo",
                2);

    }

    /**
     * Tears down the text fixture
     *
     * Always called after every etst case
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
        assertEquals(LocalTime.of(12, 00), departure.getDepartureTime());
        assertEquals("F1",departure.getLine());
        assertEquals("234", departure.getTrainNumber();
        assertEquals("Oslo", departure.getDestination());

    }
}