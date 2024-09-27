package com.techinnovate.compliancedashboard.models;

import java.time.ZonedDateTime;
import java.util.Objects;

public class ComplianceRulesModel {
        private final String ruleId;
        private final String ruleName;
        private final String description;
        private final String framework;
        private final int threshold;
        private final String action;
        private final String background;
        private final String createdAt;
        private final String updatedAt;

        // Constructor
        public ComplianceRulesModel(String ruleId, String ruleName, String description, String framework,
                                    int threshold, String action, String background,
                                    String createdAt, String updatedAt) {
            this.ruleId = ruleId;
            this.ruleName = ruleName;
            this.description = description;
            this.framework = framework;
            this.threshold = threshold;
            this.action = action;
            this.background = background;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        // Getters
        public String getRuleId() { return ruleId; }
        public String getRuleName() { return ruleName; }
        public String getDescription() { return description; }
        public String getFramework() { return framework; }
        public int getThreshold() { return threshold; }
        public String getAction() { return action; }
        public String getBackground() { return background; }
        public String getCreatedAt() { return createdAt; }
        public String getUpdatedAt() { return updatedAt; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplianceRulesModel that = (ComplianceRulesModel) o;
        return threshold == that.threshold &&
                Objects.equals(ruleId, that.ruleId) &&
                Objects.equals(ruleName, that.ruleName) &&
                Objects.equals(description, that.description) &&
                Objects.equals(framework, that.framework) &&
                Objects.equals(action, that.action) &&
                Objects.equals(background, that.background) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }
    @Override
    public int hashCode() {
        return Objects.hash(ruleId, ruleName, description, framework, threshold, action, background, createdAt, updatedAt);
    }
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String ruleId;
        private String ruleName;
        private String description;
        private String framework;
        private int threshold;
        private String action;
        private String background;
        private String createdAt;
        private String updatedAt;

        public Builder withRuleId(String ruleId) {
            this.ruleId = ruleId;
            return this;
        }

        public Builder withRuleName(String ruleName) {
            this.ruleName = ruleName;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withFramework(String framework) {
            this.framework = framework;
            return this;
        }

        public Builder withThreshold(int threshold) {
            this.threshold = threshold;
            return this;
        }

        public Builder withAction(String action) {
            this.action = action;
            return this;
        }

        public Builder withBackground(String background) {
            this.background = background;
            return this;
        }

        public Builder withCreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder withUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public ComplianceRulesModel build() {
            return new ComplianceRulesModel(ruleId, ruleName, description, framework, threshold, action, background, createdAt, updatedAt);
        }
    }
}

