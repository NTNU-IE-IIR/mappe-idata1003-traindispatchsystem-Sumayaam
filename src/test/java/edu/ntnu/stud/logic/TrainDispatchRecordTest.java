package edu.ntnu.stud.logic;

import edu.ntnu.stud.entity.TrainDeparture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit test for TrainDispatchRecord.
 * the following is/should be tested:
 * <p> The following tests covers both the required functionality
 * which is expected to be delivered/supplied by the train dispatch record class (positive test), and
 * the robustness of the class when it is being used in ways it was not intended.(negative test)
 * <ul>
 *    <li> Test adding a train departure by using valid input
 *    </li>
 *    <li> Test adding a train departure by using invalid input {@code null}</li>
 *    <li> Test adding a train departure with an already existing train number</li>
 *    <li> Test adding a train departure by using train number</li>
 *    <Li> Test adding a train departure with a train number that does not exist</Li>
 *    <li> Test adding a train departure by using the same train number on more than one train</li>
 *    <li> Test finding a train departure by using destination</li>
 *    <Li> Test finding a train departure by using a destination that does not exist</Li>
 *    <li> Test finding a train departure by using invalid input as empty string and {@code null}</li>
 *    <li> Test finding a train departure by using valid input</li>
 *    <li> Test finding a train departure by using invalid input such as empty String and {@code null}</li>
 *    <li> Test removing a train departure taking the delay into account</li>
 *    <li> Test that train departures will be sorted as a list depending on departure time</li>
 *    <li Tests that train departures with the same train numbers will not be added to the sorted list</li>
 *    <li> Test that train departures will not be removed from the record,
 *    if the delay is later than the given time to this specific </li>
 *    <Li> Test that the compareTo method works correctly</Li>
 *     </ul>
 */

class TrainDispatchRecordTest {
  private TrainDispatchRecord trainDispatchRecord;

  /**
   * Sets up the test fixture for each test.
   * <p>
   * This method is called before each test case method.
   */
  @BeforeEach
  public void setUp() {
    this.trainDispatchRecord = new TrainDispatchRecord();
    this.trainDispatchRecord.addTrainDeparture(new TrainDeparture(LocalTime.parse("12:00"),
        "F1",
        789,
        "Oslo",
        LocalTime.parse("10:00"), 4));
    this.trainDispatchRecord.addTrainDeparture(new TrainDeparture(LocalTime.parse("14:00"),
        "L1",
        123,
        "Stockholm",
        LocalTime.parse("00:10"), 5));
    this.trainDispatchRecord.addTrainDeparture(new TrainDeparture(LocalTime.parse("15:00"),
        "L2",
        321,
        "Copenhagen",
        LocalTime.parse("00:10"), 6));
  }

  /**
   * Test that a train departure is added correctly,
   * by using valid input,using the size of the train record as verification.
   *
   *
   * <p>
   * Positive test
   */

  @Test
  public void testAddingTrainDepartureByUsingValidInput() {
    TrainDeparture trainDeparture = new TrainDeparture(LocalTime.parse("16:00"),
        "L3",
        456,
        "Berlin",
        LocalTime.parse("00:40"), 7);
    this.trainDispatchRecord.addTrainDeparture(trainDeparture);
    assertEquals(trainDeparture,
        this.trainDispatchRecord.findTrainDepartureByTrainNumber(trainDeparture.getTrainNumber()));
    assertEquals(4, this.trainDispatchRecord.getNumberOfTrainDepartures());
  }

  /**
   * Test adding a train departure with a train number that is already in the register
   * <p>
   * positive test
   */
  @Test
  public void testAddingTrainDepartureWithAlreadyExistingTrainNumber() {
    TrainDeparture trainDeparture = new TrainDeparture(LocalTime.parse("16:00"),
        "L3",
        321,
        "Berlin",
        LocalTime.parse("00:20"), 7);
    boolean trainDepartureAdded = this.trainDispatchRecord.addTrainDeparture(trainDeparture);
    assertFalse(trainDepartureAdded);
    assertEquals(3, this.trainDispatchRecord.getNumberOfTrainDepartures());
  }

  /**
   * Test adding a student by using invalid input (null)
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
   * Test that a train departure is found by using a train number that does not exist in the record.
   * <p>
   * positive test.
   */
  @Test
  public void tesFindingTrainDepartureByUsingTrainNumberThatDoesNotExist() {
    TrainDeparture trainDeparture = this.trainDispatchRecord.findTrainDepartureByTrainNumber(0);
    assertNull(trainDeparture);
  }


  /**
   *Tests that a trainDeparture with the same train numbers are not added to the record.
   *<p></p>
   * Negative test.
   */

  @Test


  public void testAddingTrainDepartureWithSameTrainNumber() {
    TrainDeparture trainDeparture1 = new TrainDeparture(LocalTime.parse("16:00"),
        "L3",
        456,
        "Berlin",
        LocalTime.parse("00:40"), 7);
    TrainDeparture trainDeparture2 = new TrainDeparture(LocalTime.parse("16:00"),
        "L3",
        456,
        "Trondheim",
        LocalTime.parse("00:20"), 7);

    boolean firstTrainDepartureAdded = this.trainDispatchRecord.addTrainDeparture(trainDeparture1);
    boolean secondTrainDepartureAdded = this.trainDispatchRecord.addTrainDeparture(trainDeparture2);
    assertTrue(firstTrainDepartureAdded);
    assertFalse(secondTrainDepartureAdded);
  }

  /**
   * Test that a train departure is found by using a valid destination.
   * <p>
   * positive test
   */
  @Test
  public void testFindingTrainDepartureByUsingDestination() {
    TrainDeparture trainDeparture =
        this.trainDispatchRecord.findTrainDepartureByDestination("Oslo");
    assertNotNull(trainDeparture);
    assertEquals("Oslo", trainDeparture.getDestination());
  }

  /**
   * Test finding a train departure by using a destination that does not exist.
   * <p>
   * positive test
   */

  @Test
  public void testFindingTrainDepartureByUsingDestinationThatDoesNotExist() {
    TrainDeparture trainDeparture =
        this.trainDispatchRecord.findTrainDepartureByDestination("Trondheim");
    assertNull(trainDeparture);
  }

  /**
   * Test adding a train departure by using invalid input {code null} or blank-or empty String
   * and white space
   * <p>
   * negative test
   */
  @Test

  public void testFindingTrainDepartureByUsingInvalidDestination() {
    assertNull(this.trainDispatchRecord.findTrainDepartureByDestination(null));
    assertNull(this.trainDispatchRecord.findTrainDepartureByDestination(""));
    assertNull(this.trainDispatchRecord.findTrainDepartureByDestination(" "));

  }

  /**
   * Tests that train departures will not be removed from the record,
   * if the delay is later than the given departure time.
   * <p>
   * positive test
   */

  @Test
  public void testRemovingTrainDeparturesFromBefore() {

    assertEquals(3, this.trainDispatchRecord.getNumberOfTrainDepartures());
    this.trainDispatchRecord.removeTrainDeparturesFromBefore(LocalTime.parse("16:00"));
    assertEquals(1, this.trainDispatchRecord.getNumberOfTrainDepartures());
  }

  /**
   * Tests that train departures is removed taking the delay into account.
   * <p></p>
   * positive test
   */

  @Test

  public void testsTrainDeparturesBeforeGivenTimeRemainsTakingDelayIntoAccount() {
    assertEquals(3, this.trainDispatchRecord.getNumberOfTrainDepartures());
    this.trainDispatchRecord.removeTrainDeparturesFromBefore(LocalTime.parse("15:00"));
    assertEquals(2, this.trainDispatchRecord.getNumberOfTrainDepartures());
  }

  /**
   * Ensures that a train departure with a duplicate train number is not added to the record.
   * <p>
   * positive test
   */
  @Test
  public void testAddingDuplicateTrainNumbersToTheSortedTrainDeparture() {
    TrainDeparture DuplicateDeparture1 = new TrainDeparture(LocalTime.parse("16:00"),
        "L3",
        123,
        "Helsinki",
        LocalTime.parse("00:40"), 7);
    assertFalse(this.trainDispatchRecord.addTrainDeparture(DuplicateDeparture1));
    assertEquals(3, this.trainDispatchRecord.getTrainDeparturesSortedByDepartureTime().size());
  }


  /**
   * Tests that the list of train departures are sorted by departure time.
   * <p>
   * positive test
   */
  @Test
  public void testGetSortedTrainDeparture() {
    ArrayList<TrainDeparture> sortedTrainDepartureList =
        this.trainDispatchRecord.getTrainDeparturesSortedByDepartureTime();
    assertEquals(3, sortedTrainDepartureList.size());
  }

  /**
   * Test that the compare To method works as it should
   * <p></p>
   * positive test
   */

  @Test
  public void testCompareTo() {
    TrainDeparture trainDeparture1 = new TrainDeparture(LocalTime.parse("16:00"),
        "L3",
        123,
        "Helsinki",
        LocalTime.parse("00:40"), 7);
    TrainDeparture trainDeparture2 = new TrainDeparture(LocalTime.parse("15:00"),
        "L4",
        543,
        "Trondheim",
        LocalTime.parse("00:20"),
        7);
    TrainDeparture trainDeparture3 = new TrainDeparture(LocalTime.parse("17:00"),
        "F3",
        393,
        "Bergen",
        LocalTime.parse("00:00"), -1);
    assertTrue(trainDeparture1.compareTo(trainDeparture2) > 0);
    assertTrue(trainDeparture2.compareTo(trainDeparture3) < 0);


  }

}



