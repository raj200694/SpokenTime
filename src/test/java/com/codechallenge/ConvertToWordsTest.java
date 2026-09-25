package com.codechallenge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConvertToWordsTest {

    @Test
    void shouldConvertSupportedNumbers() {
        assertEquals("one", ConvertToWords.convert(1));
        assertEquals("fifteen", ConvertToWords.convert(15));
        assertEquals("twenty-five", ConvertToWords.convert(25));
        assertEquals("fifty-nine", ConvertToWords.convert(59));
    }

    @Test
    void shouldRejectUnsupportedNumbers() {
        assertThrows(IllegalArgumentException.class, () -> ConvertToWords.convert(0));
        assertThrows(IllegalArgumentException.class, () -> ConvertToWords.convert(60));
    }

    @Test
    void throwsForNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> ConvertToWords.convert(-1));
    }
    @Test
    void throwsForLargeOutOfRangeNumber() {
        assertThrows(IllegalArgumentException.class, () -> ConvertToWords.convert(1000));
    }
}
