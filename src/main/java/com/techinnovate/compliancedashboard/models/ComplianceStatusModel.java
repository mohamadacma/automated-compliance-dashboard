package com.techinnovate.compliancedashboard.models;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public class ComplianceStatusModel {
    private final String statusId;
    private final String framework;
    private final String status;
    private final ZonedDateTime lastUpdated;
    private final List<ComplianceViolationModel> activeViolations;
    private final String businessUnit;
    private final String lastAssessmentId;
    private final int riskScore;
    private final ZonedDateTime nextAssessmentDue;

    private ComplianceStatusModel(String statusId, String framework, String status,
                                  ZonedDateTime lastUpdated, List<ComplianceViolationModel> activeViolations,
                                  String businessUnit, String lastAssessmentId, int riskScore,
                                  ZonedDateTime nextAssessmentDue) {
        this.statusId = statusId;
        this.framework = framework;
        this.status = status;
        this.lastUpdated = lastUpdated;
        this.activeViolations = activeViolations;
        this.businessUnit = businessUnit;
        this.lastAssessmentId = lastAssessmentId;
        this.riskScore = riskScore;
        this.nextAssessmentDue = nextAssessmentDue;
    }

    public String getStatusId() { return statusId; }
    public String getFramework() { return framework; }
    public String getStatus() { return status; }
    public ZonedDateTime getLastUpdated() { return lastUpdated; }
    public List<ComplianceViolationModel> getActiveViolations() { return activeViolations; }
    public String getBusinessUnit() { return businessUnit; }
    public String getLastAssessmentId() { return lastAssessmentId; }
    public int getRiskScore() { return riskScore; }
    public ZonedDateTime getNextAssessmentDue() { return nextAssessmentDue; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplianceStatusModel that = (ComplianceStatusModel) o;
        return riskScore == that.riskScore &&
                Objects.equals(statusId, that.statusId) &&
                Objects.equals(framework, that.framework) &&
                Objects.equals(status, that.status) &&
                Objects.equals(lastUpdated, that.lastUpdated) &&
                Objects.equals(activeViolations, that.activeViolations) &&
                Objects.equals(businessUnit, that.businessUnit) &&
                Objects.equals(lastAssessmentId, that.lastAssessmentId) &&
                Objects.equals(nextAssessmentDue, that.nextAssessmentDue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, framework, status, lastUpdated, activeViolations,
                businessUnit, lastAssessmentId, riskScore, nextAssessmentDue);
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String statusId;
        private String framework;
        private String status;
        private ZonedDateTime lastUpdated;
        private List<ComplianceViolationModel> activeViolations;
        private String businessUnit;
        private String lastAssessmentId;
        private int riskScore;
        private ZonedDateTime nextAssessmentDue;

        public Builder withStatusId(String statusId) {
            this.statusId = statusId;
            return this;
        }

        public Builder withFramework(String framework) {
            this.framework = framework;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withLastUpdated(ZonedDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public Builder withActiveViolations(List<ComplianceViolationModel> activeViolations) {
            this.activeViolations = activeViolations;
            return this;
        }

        public Builder withBusinessUnit(String businessUnit) {
            this.businessUnit = businessUnit;
            return this;
        }

        public Builder withLastAssessmentId(String lastAssessmentId) {
            this.lastAssessmentId = lastAssessmentId;
            return this;
        }

        public Builder withRiskScore(int riskScore) {
            this.riskScore = riskScore;
            return this;
        }

        public Builder withNextAssessmentDue(ZonedDateTime nextAssessmentDue) {
            this.nextAssessmentDue = nextAssessmentDue;
            return this;
        }

        public ComplianceStatusModel build() {
            return new ComplianceStatusModel(statusId, framework, status, lastUpdated,
                    activeViolations, businessUnit, lastAssessmentId, riskScore, nextAssessmentDue);
        }
    }
}