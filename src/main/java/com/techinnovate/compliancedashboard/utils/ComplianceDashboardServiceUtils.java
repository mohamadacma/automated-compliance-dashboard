package com.techinnovate.compliancedashboard.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;
import java.util.regex.Pattern;

public final class ComplianceDashboardServiceUtils {
    private static final Pattern INVALID_CHARACTER_PATTERN = Pattern.compile("[\"'\\\\]");
    static final int RULE_ID_LENGTH = 8;

    private ComplianceDashboardServiceUtils() {
    }

    /**
     * Validates a string to ensure it is not blank and does not contain invalid characters.
     *
     * @param stringToValidate the string to validate
     * @return true if the string is valid, false otherwise
     */
    public static boolean isValidString(String stringToValidate) {
        if (StringUtils.isBlank(stringToValidate)) {
            return false;
        } else {
            return !INVALID_CHARACTER_PATTERN.matcher(stringToValidate).find();
        }
    }

    /**
     * Validates if a date string is in ISO 8601 format.
     *
     * @param dateString the date string to validate
     * @return true if the date string is valid, false otherwise
     */
    public static boolean isValidIsoDate(String dateString) {
        try {
            DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_DATE_TIME;
            OffsetDateTime.parse(dateString, isoFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Generates a new unique identifier for a compliance rule.
     *
     * @return a new unique rule ID as a string
     */
    public static String generateRuleId() {
        return "RULE-" + UUID.randomUUID().toString().substring(0, RULE_ID_LENGTH);
    }

    /**
     * Validates if the given threshold is within an acceptable range.
     *
     * @param threshold the threshold to validate
     * @return true if the threshold is valid, false otherwise
     */
    public static boolean isValidThreshold(int threshold) {
        // Adjust the range as per your compliance rule requirements
        return threshold >= 0 && threshold <= 100;
    }

    /**
     * Validates if the given framework is a recognized compliance framework.
     *
     * @param framework the framework to validate
     * @return true if the framework is valid, false otherwise
     */
    public static boolean isValidFramework(String framework) {
        // Add your recognized frameworks here
        String[] validFrameworks = {"SOC 2", "ISO 27001", "GDPR", "HIPAA", "PCI DSS"};
        return StringUtils.isNotBlank(framework) &&
                java.util.Arrays.asList(validFrameworks).contains(framework.toUpperCase());
    }
}