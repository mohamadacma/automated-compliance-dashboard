package com.techinnovate.compliancedashboard.models;


import java.time.ZonedDateTime;
import java.util.Objects;

public class AuditLogModel {
    private final String logId;
    private final String actionType;
    private final String userId;
    private final ZonedDateTime timestamp;
    private final String details;
    private final String relatedEntityId;
    private final String ipAddress;

    private AuditLogModel(String logId, String actionType, String userId, ZonedDateTime timestamp,
                          String details, String relatedEntityId, String ipAddress) {
        this.logId = logId;
        this.actionType = actionType;
        this.userId = userId;
        this.timestamp = timestamp;
        this.details = details;
        this.relatedEntityId = relatedEntityId;
        this.ipAddress = ipAddress;
    }

    public String getLogId() { return logId; }
    public String getActionType() { return actionType; }
    public String getUserId() { return userId; }
    public ZonedDateTime getTimestamp() { return timestamp; }
    public String getDetails() { return details; }
    public String getRelatedEntityId() { return relatedEntityId; }
    public String getIpAddress() { return ipAddress; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditLogModel that = (AuditLogModel) o;
        return Objects.equals(logId, that.logId) &&
                Objects.equals(actionType, that.actionType) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(details, that.details) &&
                Objects.equals(relatedEntityId, that.relatedEntityId) &&
                Objects.equals(ipAddress, that.ipAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId, actionType, userId, timestamp, details, relatedEntityId, ipAddress);
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String logId;
        private String actionType;
        private String userId;
        private ZonedDateTime timestamp;
        private String details;
        private String relatedEntityId;
        private String ipAddress;

        public Builder withLogId(String logId) {
            this.logId = logId;
            return this;
        }

        public Builder withActionType(String actionType) {
            this.actionType = actionType;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withTimestamp(ZonedDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withDetails(String details) {
            this.details = details;
            return this;
        }

        public Builder withRelatedEntityId(String relatedEntityId) {
            this.relatedEntityId = relatedEntityId;
            return this;
        }

        public Builder withIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public AuditLogModel build() {
            return new AuditLogModel(logId, actionType, userId, timestamp, details, relatedEntityId, ipAddress);
        }
    }
}