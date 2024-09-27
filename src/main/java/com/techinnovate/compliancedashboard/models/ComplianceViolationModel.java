package com.techinnovate.compliancedashboard.models;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public class ComplianceViolationModel {
    private final String violationId;
    private final String description;
    private final ZonedDateTime detectedAt;
    private final String resolutionStatus;
    private final ZonedDateTime resolvedAt;
    private final String businessUnit;
    private final String severity;
    private final String ruleId;
    private final String frameworkId;
    private final String assignedTo;
    private final List<String> affectedAssets;
    private final String remediationPlan;
    private final List<String> evidenceLinks;

    private ComplianceViolationModel(String violationId, String description, ZonedDateTime detectedAt,
                                     String resolutionStatus, ZonedDateTime resolvedAt, String businessUnit,
                                     String severity, String ruleId, String frameworkId, String assignedTo,
                                     List<String> affectedAssets, String remediationPlan, List<String> evidenceLinks) {
        this.violationId = violationId;
        this.description = description;
        this.detectedAt = detectedAt;
        this.resolutionStatus = resolutionStatus;
        this.resolvedAt = resolvedAt;
        this.businessUnit = businessUnit;
        this.severity = severity;
        this.ruleId = ruleId;
        this.frameworkId = frameworkId;
        this.assignedTo = assignedTo;
        this.affectedAssets = affectedAssets;
        this.remediationPlan = remediationPlan;
        this.evidenceLinks = evidenceLinks;
    }

    public String getViolationId() { return violationId; }
    public String getDescription() { return description; }
    public ZonedDateTime getDetectedAt() { return detectedAt; }
    public String getResolutionStatus() { return resolutionStatus; }
    public ZonedDateTime getResolvedAt() { return resolvedAt; }
    public String getBusinessUnit() { return businessUnit; }
    public String getSeverity() { return severity; }
    public String getRuleId() { return ruleId; }
    public String getFrameworkId() { return frameworkId; }
    public String getAssignedTo() { return assignedTo; }
    public List<String> getAffectedAssets() { return affectedAssets; }
    public String getRemediationPlan() { return remediationPlan; }
    public List<String> getEvidenceLinks() { return evidenceLinks; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplianceViolationModel that = (ComplianceViolationModel) o;
        return Objects.equals(violationId, that.violationId) &&
                Objects.equals(description, that.description) &&
                Objects.equals(detectedAt, that.detectedAt) &&
                Objects.equals(resolutionStatus, that.resolutionStatus) &&
                Objects.equals(resolvedAt, that.resolvedAt) &&
                Objects.equals(businessUnit, that.businessUnit) &&
                Objects.equals(severity, that.severity) &&
                Objects.equals(ruleId, that.ruleId) &&
                Objects.equals(frameworkId, that.frameworkId) &&
                Objects.equals(assignedTo, that.assignedTo) &&
                Objects.equals(affectedAssets, that.affectedAssets) &&
                Objects.equals(remediationPlan, that.remediationPlan) &&
                Objects.equals(evidenceLinks, that.evidenceLinks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(violationId, description, detectedAt, resolutionStatus, resolvedAt,
                businessUnit, severity, ruleId, frameworkId, assignedTo,
                affectedAssets, remediationPlan, evidenceLinks);
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String violationId;
        private String description;
        private ZonedDateTime detectedAt;
        private String resolutionStatus;
        private ZonedDateTime resolvedAt;
        private String businessUnit;
        private String severity;
        private String ruleId;
        private String frameworkId;
        private String assignedTo;
        private List<String> affectedAssets;
        private String remediationPlan;
        private List<String> evidenceLinks;

        public Builder withViolationId(String violationId) {
            this.violationId = violationId;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withDetectedAt(ZonedDateTime detectedAt) {
            this.detectedAt = detectedAt;
            return this;
        }

        public Builder withResolutionStatus(String resolutionStatus) {
            this.resolutionStatus = resolutionStatus;
            return this;
        }

        public Builder withResolvedAt(ZonedDateTime resolvedAt) {
            this.resolvedAt = resolvedAt;
            return this;
        }

        public Builder withBusinessUnit(String businessUnit) {
            this.businessUnit = businessUnit;
            return this;
        }

        public Builder withSeverity(String severity) {
            this.severity = severity;
            return this;
        }

        public Builder withRuleId(String ruleId) {
            this.ruleId = ruleId;
            return this;
        }

        public Builder withFrameworkId(String frameworkId) {
            this.frameworkId = frameworkId;
            return this;
        }

        public Builder withAssignedTo(String assignedTo) {
            this.assignedTo = assignedTo;
            return this;
        }

        public Builder withAffectedAssets(List<String> affectedAssets) {
            this.affectedAssets = affectedAssets;
            return this;
        }

        public Builder withRemediationPlan(String remediationPlan) {
            this.remediationPlan = remediationPlan;
            return this;
        }

        public Builder withEvidenceLinks(List<String> evidenceLinks) {
            this.evidenceLinks = evidenceLinks;
            return this;
        }

        public ComplianceViolationModel build() {
            return new ComplianceViolationModel(violationId, description, detectedAt, resolutionStatus,
                    resolvedAt, businessUnit, severity, ruleId, frameworkId, assignedTo,
                    affectedAssets, remediationPlan, evidenceLinks);
        }
    }
}
