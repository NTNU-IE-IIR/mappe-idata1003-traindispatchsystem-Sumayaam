package edu.ntnu.stud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TrainDispatchRecordTest {
    private TrainDispatchRecord trainDispatchRecord;

 /**
     * Sets up the test fixture for each test.
     *
     * This method is called before each test case method.
     */
    @BeforeEach
    public void setUp() {
        this.trainDispatchRecord = new TrainDispatchRecord();
        this.trainDispatchRecord.addTrainDeparture(new TrainDeparture(LocalTime.of(12, 0),
                "F1",
                789,
                "Oslo",
                LocalTime.of(0, 10), 4));
        this.trainDispatchRecord.addTrainDeparture(new TrainDeparture(LocalTime.of(14, 0),
                "L1",
                123,
                "Stockholm",
                LocalTime.of(0, 20), 5));
        this.trainDispatchRecord.addTrainDeparture(new TrainDeparture(LocalTime.of(15, 0),
                "L2",
                321,
                "Copenhagen",
                LocalTime.of(0, 30), 6));
    }

/**
 * Test that a train departure is added correctly,
 * and as well as the number of train departures.
 *<p>
 *Positive test
 */

@Test
    public void testAddingTrainDepartureByUsingValidInput() {
    TrainDeparture trainDeparture = new TrainDeparture(LocalTime.of(16, 0),
            "L3",
            456,
            "Berlin",
            LocalTime.of(0, 40), 7);
    this.trainDispatchRecord.addTrainDeparture(trainDeparture);
    assertEquals(trainDeparture, this.trainDispatchRecord.findTrainDepartureByTrainNumber(trainDeparture.getTrainNumber()));
    assertEquals(4, this.trainDispatchRecord.getNumberOfTrainDepartures());
}
/**
 * Test adding a student by using invalid input (null).
 * <p>
 * Negative test.
 */

@Test
    public void testsAddingTrainDepartureByUsingInvalidInput() {
    this.trainDispatchRecord.addTrainDeparture(null);
    assertEquals(3, this.trainDispatchRecord.getNumberOfTrainDepartures());
}


/**
 * Test that a train departure is found by using a valid train number.
 * <p>
 * Positive test.
 */
@Test
public void testFindingTrainDepartureByUsingTrainNumber() {
    TrainDeparture trainDeparture = this.trainDispatchRecord.findTrainDepartureByTrainNumber(789);
    assertEquals(789, trainDeparture.getTrainNumber());
}

/**
 * Tests that a train departure is not found by using an invalid train number.
 *
 */
@Test
public void testFindingTrainDepartureByUsingInvalidTrainNumber() {
    TrainDeparture trainDeparture = this.trainDispatchRecord.findTrainDepartureByTrainNumber(0);
    assertNull(trainDeparture);
}

/**
 * Test that a train departure is not found by using an invalid train number.
 * <p>
 * Negative test.
 */
@Test
public void testFindingTrainDepartureByUsingInvalidTrainNumberShouldReturnNull() {
    TrainDeparture trainDeparture = this.trainDispatchRecord.findTrainDepartureByTrainNumber(0);
    assertNull(trainDeparture);
}
}

