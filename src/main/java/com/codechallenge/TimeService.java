package com.codechallenge;

import java.time.LocalTime;

public final class TimeService {

    public String speak(LocalTime time) {
        if (time == null) {
            throw new IllegalArgumentException("Time must not be null.");
        }

        int hour = time.getHour();
        int minute = time.getMinute();

        if (hour == 0 && minute == 0) {
            return "midnight";
        }

        if (hour == 12 && minute == 0) {
            return "noon";
        }

        int spokenHour = toTwelveHourClock(hour);

        if (minute == 0) {
            return ConvertToWords.convert(spokenHour) + " o'clock";
        }

        if (minute == 15) {
            return "quarter past " + ConvertToWords.convert(spokenHour);
        }

        if (minute == 30) {
            return "half past " + ConvertToWords.convert(spokenHour);
        }

        if (minute == 45) {
            return "quarter to " + ConvertToWords.convert(nextHour(spokenHour));
        }

        if (minute <= 29) {
            return ConvertToWords.convert(minute)
                    + " past "
                    + ConvertToWords.convert(spokenHour);
        }

        /*
         * The challenge explicitly gives 06:32 -> "six thirty-two".
         * To preserve that documented behaviour, minutes 31 and 32 are
         * spoken directly. From 33 onward the examples use the "to" form.
         */
        if (minute <= 32) {
            return ConvertToWords.convert(spokenHour)
                    + " "
                    + ConvertToWords.convert(minute);
        }

        return ConvertToWords.convert(60 - minute)
                + " to "
                + ConvertToWords.convert(nextHour(spokenHour));
    }

    private int toTwelveHourClock(int hour) {
        int result = hour % 12;
        return result == 0 ? 12 : result;
    }

    private int nextHour(int hour) {
        return hour == 12 ? 1 : hour + 1;
    }
}
