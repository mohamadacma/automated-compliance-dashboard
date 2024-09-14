package com.techinnovate.compliancedashboard.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

/**
 * Represents a record in the ComplianceRules table.
 */
@DynamoDBTable(tableName = "ComplianceRules")
public class ComplianceRuleRecord {
    private String ruleId;
    private String ruleName;
    private String description;
    private String framework;
    private int threshold;
    private String action;
    private String background;
    private String createdAt;  // store in ISO 8601 format(YYYY-MM-DDThh:mm:ss.sssZ)
    private String updatedAt;  // store in ISO 8601 format(YYYY-MM-DDThh:mm:ss.sssZ)

    @DynamoDBHashKey(attributeName = "ruleId")
    public String getRuleId() { return ruleId; }
    public void setRuleId(String ruleId) { this.ruleId = ruleId; }

    @DynamoDBAttribute(attributeName = "ruleName")
    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @DynamoDBAttribute(attributeName = "framework")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "FrameworkIndex")
    public String getFramework() { return framework; }
    public void setFramework(String framework) { this.framework = framework; }

    @DynamoDBAttribute(attributeName = "threshold")
    public int getThreshold() { return threshold; }
    public void setThreshold(int threshold) { this.threshold = threshold; }

    @DynamoDBAttribute(attributeName = "action")
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    @DynamoDBAttribute(attributeName = "background")
    public String getBackground() { return background; }
    public void setBackground(String background) { this.background = background; }

    @DynamoDBAttribute(attributeName = "createdAt")
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    @DynamoDBAttribute(attributeName = "updatedAt")
    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplianceRuleRecord that = (ComplianceRuleRecord) o;
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

    @Override
    public String toString() {
        return "ComplianceRuleRecord{" +
                "ruleId='" + ruleId + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", description='" + description + '\'' +
                ", framework='" + framework + '\'' +
                ", threshold=" + threshold +
                ", action='" + action + '\'' +
                ", background='" + background + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
