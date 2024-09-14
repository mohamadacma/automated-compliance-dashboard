package com.techinnovate.compliancedashboard.activity.results;

import com.techinnovate.compliancedashboard.models.ComplianceRulesModel;

/**
 * Represents the result of adding a new compliance rule.
 */
public class AddComplianceRuleResult {
    private final ComplianceRulesModel complianceRule;

    private AddComplianceRuleResult(Builder builder) {
        this.complianceRule = builder.complianceRule;
    }

    public ComplianceRulesModel getComplianceRule() {
        return complianceRule;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ComplianceRulesModel complianceRule;

        public Builder withComplianceRule(ComplianceRulesModel complianceRule) {
            this.complianceRule = complianceRule;
            return this;
        }

        public AddComplianceRuleResult build() {
            return new AddComplianceRuleResult(this);
        }
    }

    @Override
    public String toString() {
        return "AddComplianceRuleResult{" +
                "complianceRule=" + complianceRule +
                '}';
    }
}