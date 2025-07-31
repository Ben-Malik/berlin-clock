package com.berlinclock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Units tests for the {@linkplain BerlinClock#getBerlinClock(String)}
 *
 * @author Ben-Malik Tchamalam
 */
public class BerlinClockTest {

    private final BerlinClock berlinClock = new BerlinClock();

    @Test
    public void getBerlinClockThrowsIllegalArgumentExceptionWithCorrectWhenGivenTimeStringIsEmpty() {
        var emptyString = "";
        var exception = assertThrows(IllegalArgumentException.class, () -> {
           berlinClock.getBerlinClock(emptyString);
        });

        assertEquals("The time cannot be null or blank.", exception.getMessage());
    }

    @Test
    public void getBerlinClockThrowsIllegalArgumentExceptionWithCorrectWhenGivenTimeStringIsNull() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            berlinClock.getBerlinClock(null);
        });

        assertEquals("The time cannot be null or blank.", exception.getMessage());
    }

    @Test
    public void getBerlinClockThrowsIllegalArgumentExceptionWithCorrectWhenGivenTimeHasAnInvalidFormat() {
        var invalidTime = "25:56:12";
        var exception = assertThrows(IllegalArgumentException.class, () -> {
           berlinClock.getBerlinClock(invalidTime);
        });

        assertEquals("Invalid time. Make sure the time is in the format: hh:mm:ss.", exception.getMessage());
    }

    @Test
    public void getBerlinClockThrowsIllegalArgumentExceptionWithCorrectWhenGivenTimeIsNotInTimeFormats() {
        var wrongInput = "invalidTime";
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            berlinClock.getBerlinClock(wrongInput);
        });

        assertEquals("Invalid time. Make sure the time is in the format: hh:mm:ss.", exception.getMessage());
    }

    @Test
    public void getBerlinClockReturnsCorrectBerlinClock() {
        assertBerlinClock("YOOOOOOOOOOOOOOOOOOOOOOO", "00:00:00");
        assertBerlinClock("ORRRRRRROYYRYYRYYRYYYYYY", "23:59:59");
        assertBerlinClock("YRRROROOOYYRYYRYYRYOOOOO", "16:50:06");
        assertBerlinClock("ORROOROOOYYRYYRYOOOOYYOO", "11:37:01");
    }

    private void assertBerlinClock(String expectedBerlinClock, String time) {
        var actualBerlinClock = berlinClock.getBerlinClock(time);
        assertEquals(expectedBerlinClock, actualBerlinClock, "Berlin clock was expected to be " + expectedBerlinClock + " but was " + actualBerlinClock);

    }
}
