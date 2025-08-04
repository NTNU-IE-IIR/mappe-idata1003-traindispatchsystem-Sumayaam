package edu.ntnu.stud.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

/**
 * Test class for TrainStationClock
 * The following must be tested:
 * <ul>
 *     <li>Verifying that the clock is created with correct initial time</li>
 *     <li>setting the time to a valid later time and checking the update</li>
 *     <li>Attempting to set the time to an earlier time and verifying that the change will not take place</li>
 *     <Li>Trying to set the time to a value outside the 00:00 to 23.59 rang and verifying that it will not update </Li>
 *     <Li>Attempting to set the time to null and verifying that the update does not occur</Li>
 *     </ul>
 *
 *
 *
 */

class TrainStationClockTest {

    private TrainStationClock trainStationClock;

    /**
     * Sets up the text fixture,
     * Always called before each test case method.
     */

    @BeforeEach
    public void setUp() {
        trainStationClock = new TrainStationClock();
    }

    /**
     * Test to ensure that the clock is created with the correct initial time(00:00)
     * This is a positive test
     */

    @Test
    public void testCreationOfClockWithCorrectInitialTime() {
        assertEquals("00:00", trainStationClock.getCurrentTime().toString());
    }

    /**
     * Tests that the time is updated correctly when setting the time to a valid later time
     */

    @Test
    public void testSettingTimeToValidLaterTimeThanCurrentTime() {
        LocalTime newTime = LocalTime.parse("13:30");
        trainStationClock.setCurrentTime(newTime);
        assertEquals("13:30", trainStationClock.getCurrentTime().toString());
    }

    /**
     * Tests that the time is not updated when setting the time to an earlier time
     * This is a negative test
     */

    @Test
    public void testSettingTimeToEarlierTime() {
        LocalTime newTime = LocalTime.parse("13:30");
        trainStationClock.setCurrentTime(newTime);
        LocalTime earlierTime = LocalTime.parse("12:30");
        assertThrows(IllegalArgumentException.class, () -> trainStationClock.setCurrentTime(earlierTime));
    }


    /**
     * Tests that the time is not updated when setting the time to null
     */

    @Test
    public void testSettingCurrentTimeToNull() {
          LocalTime newTime = LocalTime.parse("13:30");
            trainStationClock.setCurrentTime(newTime);
            assertThrows(IllegalArgumentException.class, () -> trainStationClock.setCurrentTime(null));
        }



}