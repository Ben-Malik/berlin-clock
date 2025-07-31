package com.berlinclock;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * An implementation to the Kata Berlin Clock.
 *
 * <div>
 *     The time to be converted to its Berlin Clock version needs to be in the format of {@linkplain #TIME_FORMAT}
 *     Hence, should not be null or composed of any blank spaces.
 *     <br>
 *     For a detailed explanation of what the Berlin Clock is and how it works, please take a glance at
 *     <a href="https://agilekatas.co.uk/katas/BerlinClock-Kata">Berlin Clock</a>
 * </div>
 *
 * @author Ben-Malik Tchamalam
 */
public class BerlinClock {

    private static final String TIME_REGEX = "^(2[0-3]|[01]?[0-9]):([0-5]?[0-9]):([0-5]?[0-9])$";
    private static final String TIME_FORMAT = "HH:mm:ss";

    /**
     * Computes the Berlin Clock version of the given standard time.
     *
     * @param time The time whose Berlin clock version is sought.
     * @return The String version of Berlin clock.
     * @throws IllegalArgumentException in case the given time value is null, empty, contains a blank space or does not
     *                                  have a time format. hh:mm:ss
     */
    public String getBerlinClock(String time) {

        if (StringUtils.isBlank(time))
            throw new IllegalArgumentException("The time cannot be null or blank.");

        if (!Pattern.compile(TIME_REGEX).matcher(time).matches())
            throw new IllegalArgumentException("Invalid time. Make sure the time is in the format: hh:mm:ss.");

        var formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        var localTime = LocalTime.parse(time, formatter);

        int hours = localTime.getHour();
        int minutes = localTime.getMinute();
        int seconds = localTime.getSecond();

        return getSecondsRow(seconds) +
                getTopHoursRow(hours) +
                getBottomHoursRow(hours) +
                getTopMinutesRow(minutes) +
                getBottomMinutesRow(minutes);
    }

    private String getSecondsRow(int seconds) {
        return seconds % 2 == 0 ? "Y" : "O";
    }

    private String getTopHoursRow(int hours) {
        int topHours = hours / 5;
        return generateRowLamps(topHours, 4, 'R');
    }

    private String getBottomHoursRow(int hours) {
        int bottomHours = hours % 5;
        return generateRowLamps(bottomHours, 4, 'R');
    }

    private String getTopMinutesRow(int minutes) {
        int topMinutes = minutes / 5;

        // The rule is that every third Y should be a read (R), hence the replacement.
        // I am well aware that replacing in a string in this manner is quite expensive performance wise.
        return generateRowLamps(topMinutes, 11, 'Y').replaceAll("YYY", "YYR");
    }

    private String getBottomMinutesRow(int minutes) {
        int bottomMinutes = minutes % 5;
        return generateRowLamps(bottomMinutes, 4, 'Y');
    }

    /**
     * Generates the lamps of a given row.
     * It first compiles the lamps that need to be lit and the ones that should unlit.
     * A concatenation of the both is later on returned.
     *
     * @param litCount        The amount of lamps that should be lit.
     * @param lampsInRow      The amount of lamps in the row.
     * @param lampColour The colour of the lamp
     * @return the lamps of the row as a String.
     */
    private String generateRowLamps(int litCount, int lampsInRow, Character lampColour) {
        var litLamps =  lampColour.toString().repeat(Math.max(0, litCount));
        var unlitLamps = "O".repeat(Math.max(0, lampsInRow - litCount));

        return litLamps + unlitLamps;
    }
}

