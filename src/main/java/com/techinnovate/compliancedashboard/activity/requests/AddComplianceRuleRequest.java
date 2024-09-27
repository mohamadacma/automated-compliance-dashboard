package com.techinnovate.compliancedashboard.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = AddComplianceRuleRequest.Builder.class)
public class AddComplianceRuleRequest {
    private final String ruleName;
    private final String description;
    private final String framework;
    private final int threshold;
    private final String action;
    private final String background;
    private final String createdBy;

    private AddComplianceRuleRequest(Builder builder) {
        this.ruleName = builder.ruleName;
        this.description = builder.description;
        this.framework = builder.framework;
        this.threshold = builder.threshold;
        this.action = builder.action;
        this.background = builder.background;
        this.createdBy = builder.createdBy;
    }

    public String getRuleName() { return ruleName; }
    public String getDescription() { return description; }
    public String getFramework() { return framework; }
    public int getThreshold() { return threshold; }
    public String getAction() { return action; }
    public String getBackground() { return background; }
    public String getCreatedBy() { return createdBy; }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String ruleName;
        private String description;
        private String framework;
        private int threshold;
        private String action;
        private String background;
        private String createdBy;

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

        public Builder withCreatedBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public AddComplianceRuleRequest build() {
            return new AddComplianceRuleRequest(this);
        }
    }
}