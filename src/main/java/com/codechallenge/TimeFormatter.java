package com.codechallenge;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class TimeFormatter {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm");

    private TimeFormatter() {
    }

    public static LocalTime parse(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Time must not be null or blank.");
        }

        try {
            return LocalTime.parse(value, FORMATTER);
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException(
                    "Invalid time '" + value + "'. Expected format HH:mm.", exception);
        }
    }
}
