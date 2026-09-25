package com.codechallenge;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeServiceTest {

    private final TimeService service = new TimeService();

    @Test
    void shouldSpeakMidnight() {
        assertEquals("midnight", service.speak(LocalTime.MIDNIGHT));
    }

    @Test
    void shouldSpeakNoon() {
        assertEquals("noon", service.speak(LocalTime.NOON));
    }

    @Test
    void shouldSpeakOnTheHour() {
        assertEquals("one o'clock", service.speak(LocalTime.of(1, 0)));
        assertEquals("noon", service.speak(LocalTime.of(12, 0)));
        assertEquals("eleven o'clock", service.speak(LocalTime.of(23, 0)));
    }

    @Test
    void shouldSpeakMinutesPastTheHour() {
        assertEquals("five past two", service.speak(LocalTime.of(2, 5)));
        assertEquals("twenty-five past six", service.speak(LocalTime.of(6, 25)));
    }

    @Test
    void shouldSpeakQuarterPast() {
        assertEquals("quarter past four", service.speak(LocalTime.of(4, 15)));
    }

    @Test
    void shouldSpeakHalfPast() {
        assertEquals("half past seven", service.speak(LocalTime.of(7, 30)));
    }

    @Test
    void shouldSpeakThirtyOneAndThirtyTwoDirectly() {
        assertEquals("six thirty-one", service.speak(LocalTime.of(6, 31)));
        assertEquals("six thirty-two", service.speak(LocalTime.of(6, 32)));
    }

    @Test
    void shouldSpeakMinutesToNextHour() {
        assertEquals("twenty-five to eight", service.speak(LocalTime.of(7, 35)));
        assertEquals("twenty to nine", service.speak(LocalTime.of(8, 40)));
        assertEquals("quarter to ten", service.speak(LocalTime.of(9, 45)));
        assertEquals("ten to eleven", service.speak(LocalTime.of(10, 50)));
        assertEquals("five to twelve", service.speak(LocalTime.of(11, 55)));
    }

    @Test
    void shouldWrapNextHourAtTwelve() {
        assertEquals("five to one", service.speak(LocalTime.of(12, 55)));
        assertEquals("five to one", service.speak(LocalTime.of(0, 55)));
    }

    @Test
    void shouldCoverEveryMinute() {
        IntStream.range(0, 24)
                .boxed()
                .flatMap(hour -> IntStream.range(0, 60)
                        .mapToObj(minute -> LocalTime.of(hour, minute)))
                .forEach(time -> {
                    String result = service.speak(time);
                    assertEquals(false, result.isBlank());
                });
    }
    @Test
    void throwsForNullTime() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> timeService.speak(null));
        assertEquals("Time must not be null.", ex.getMessage());
    }
}
