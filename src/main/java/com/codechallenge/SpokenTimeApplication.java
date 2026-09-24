package com.codechallenge;

import java.time.LocalTime;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public final class SpokenTimeApplication {

    private SpokenTimeApplication() {
    }

    public static void main(String[] args) {


        String input = args.length > 0 ? args[0] : readFromStdIn();
        if (input == null || input.isBlank()) {
            System.err.println("Usage: java -jar british-time.jar <HH:MM>");
            System.exit(1);
            return;
        }

        try {
            LocalTime time = TimeFormatter.parse(args[0]);
            System.out.println(new TimeService().speak(time));
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static String readFromStdIn() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}
