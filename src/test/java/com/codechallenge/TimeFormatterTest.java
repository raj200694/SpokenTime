package com.codechallenge;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeFormatterTest {

    @Test
    void shouldParseValidTime() {
        assertEquals(LocalTime.of(12, 0), TimeFormatter.parse("12:00"));
        assertEquals(LocalTime.of(7, 35), TimeFormatter.parse("07:35"));
        assertEquals(LocalTime.of(23, 59), TimeFormatter.parse("23:59"));
    }
    @Test
    void parsesMidnight() {
        assertEquals(LocalTime.of(0, 0), TimeFormatter.parse("00:00"));
    }

    @Test
    void throwsForNullInput() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> TimeFormatter.parse(null));
        assertEquals("Time must not be null or blank.", ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void throwsForBlankInput(String value) {
        assertThrows(IllegalArgumentException.class, () -> TimeFormatter.parse(value));
    }
    @Test
    void shouldRejectInvalidTime() {
        assertThrows(IllegalArgumentException.class, () -> TimeFormatter.parse("12:60"));
        assertThrows(IllegalArgumentException.class, () -> TimeFormatter.parse("abc"));
        assertThrows(IllegalArgumentException.class, () -> TimeFormatter.parse(""));
        assertThrows(IllegalArgumentException.class, () -> TimeFormatter.parse(null));
    }
}
