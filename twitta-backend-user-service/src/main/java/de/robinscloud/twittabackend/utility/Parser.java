package de.robinscloud.twittabackend.utility;

public class Parser {
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }
}
