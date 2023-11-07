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
 * Tests that a train departure is found by using an invalid train number.
 *
 */
@Test
public void testFindingTrainDepartureByUsingInvalidTrainNumber() {
    TrainDeparture trainDeparture = this.trainDispatchRecord.findTrainDepartureByTrainNumber(0);
    assertNull(trainDeparture);
}

@Test
/**
 * Tests that a trainDeparture with same train numbers are not added to the record.
 *
 * Negative test.
 */

public void testAddingTrainDepartureWithSameTrainNumber() {
    TrainDeparture trainDeparture1 = new TrainDeparture(LocalTime.of(16, 0),
            "L3",
            456,
            "Berlin",
            LocalTime.of(0, 40), 7);
    TrainDeparture trainDeparture2 = new TrainDeparture(LocalTime.of(16, 0),
            "L3",
            456,
            "Trondheim",
            LocalTime.of(0, 20), 7);

    boolean firstTrainDepartureAdded = this.trainDispatchRecord.addTrainDeparture(trainDeparture1);
    boolean secondTrainDepartureAdded = this.trainDispatchRecord.addTrainDeparture(trainDeparture2);
    assertTrue(firstTrainDepartureAdded);
    assertFalse(secondTrainDepartureAdded);
}
/**
 * Test that a train departure is found by using a valid destination.
 *
 * positive test
 */
@Test
    public void testFindingTrainDepartureByUsingDestination() {
    TrainDeparture trainDeparture = this.trainDispatchRecord.findTrainDepartureByDestination("Oslo");
    }

/**
 *Tests that train departures from before a given time is removed from the record.
 *
 * positive test
 */

@Test
    public void testRemovingTrainDeparturesFromBefore() {
    this.trainDispatchRecord.removeTrainDeparturesFromBefore(LocalTime.of(14, 0));
    assertEquals(2, this.trainDispatchRecord.getNumberOfTrainDepartures());
}

/**
 * Tests that train departures after a given time is not removed from the record.
 *
 * negative test
 */
@Test

public void testRemovingTrainDeparturesFromAfter() {
    this.trainDispatchRecord.removeTrainDeparturesFromBefore(LocalTime.of(14, 0));
    assertEquals(2, this.trainDispatchRecord.getNumberOfTrainDepartures());
}
/**
 * Tests that train departures is returned as a sorted list depending on departure time.
 *
 * positive test
 */
@Test
public void testGetSortedTrainDeparture() {
    assertEquals(3, this.trainDispatchRecord.getSortedTrainDeparture().size());
    assertEquals(LocalTime.of(12, 0), this.trainDispatchRecord.getSortedTrainDeparture().get(0).getDepartureTime());
    assertEquals(LocalTime.of(14, 0), this.trainDispatchRecord.getSortedTrainDeparture().get(1).getDepartureTime());
    assertEquals(LocalTime.of(15, 0), this.trainDispatchRecord.getSortedTrainDeparture().get(2).getDepartureTime());
}

/**
 * Tests that train departures is returned as a sorted list depending on departure time.
 *
 * negative test
 *
 */

}

