package com.techinnovate.compliancedashboard.exceptions;

/**
 * Exception to throw when a given ruleId is not found in the database.
 */
public class ComplianceRuleNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4402165616355563974L;

    /**
     * Exception with no message or cause.
     */
    public ComplianceRuleNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public ComplianceRuleNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public ComplianceRuleNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public ComplianceRuleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}