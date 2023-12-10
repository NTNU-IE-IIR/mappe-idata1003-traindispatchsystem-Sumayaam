package edu.ntnu.stud.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TrainDepartureTest {
  private TrainDeparture departure;

  /**
   * Sets up the text fixture.
   * <p>
   * Always called before each test case method.
   */

  @BeforeEach

  public void setUp() {
    departure = new TrainDeparture(LocalTime.of(12, 0),
        "F1",
        789,
        "Oslo",
        LocalTime.of(0, 10), 4);

  }

  /**
   * Tears down the text fixture
   * <p>
   * Always called after every test case
   */

  @AfterEach
  public void teardown() {
    departure = null;

  }

  /**
   * Positive test which test valid train departure inputs.
   * <p>
   * Test that the train departure is created correctly.
   */

  @Test
  public void testValidTrainDepartureInput() {
    assertEquals(LocalTime.of(12, 0), departure.getDepartureTime());
    assertEquals("F1", departure.getLine());
    assertEquals(789, departure.getTrainNumber());
    assertEquals("Oslo", departure.getDestination());
    assertEquals(LocalTime.of(0, 10), departure.getDelay());
    assertEquals(4, departure.getTrack());
  }

  /**
   * Negative test which test invalid train departure inputs.
   * <p>
   * Tests that train departure handles invalid inputs correctly
   */
  @Test
  public void testInvalidTrainDepartureInput() {
    departure = new TrainDeparture(LocalTime.of(12, 0),
        "",
        -2,
        "",
        LocalTime.of(0, 10), 0);

    assertEquals(LocalTime.of(12, 0), departure.getDepartureTime());
    assertEquals("INVALID LINE", departure.getLine());
    assertEquals(0, departure.getTrainNumber());
    assertEquals("INVALID DESTINATION", departure.getDestination());
    assertEquals(-1, departure.getTrack());

  }

}