package com.techinnovate.compliancedashboard.models;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public class UserModel {
    private final String userId;
    private final String username;
    private final String email;
    private final String hashedPassword;
    private final List<String> roles;
    private final ZonedDateTime lastLogin;
    private final boolean isActive;

    private UserModel(String userId, String username, String email, String hashedPassword,
                      List<String> roles, ZonedDateTime lastLogin, boolean isActive) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.roles = roles;
        this.lastLogin = lastLogin;
        this.isActive = isActive;
    }

    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getHashedPassword() { return hashedPassword; }
    public List<String> getRoles() { return roles; }
    public ZonedDateTime getLastLogin() { return lastLogin; }
    public boolean isActive() { return isActive; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return isActive == userModel.isActive &&
                Objects.equals(userId, userModel.userId) &&
                Objects.equals(username, userModel.username) &&
                Objects.equals(email, userModel.email) &&
                Objects.equals(hashedPassword, userModel.hashedPassword) &&
                Objects.equals(roles, userModel.roles) &&
                Objects.equals(lastLogin, userModel.lastLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, email, hashedPassword, roles, lastLogin, isActive);
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String userId;
        private String username;
        private String email;
        private String hashedPassword;
        private List<String> roles;
        private ZonedDateTime lastLogin;
        private boolean isActive;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withHashedPassword(String hashedPassword) {
            this.hashedPassword = hashedPassword;
            return this;
        }

        public Builder withRoles(List<String> roles) {
            this.roles = roles;
            return this;
        }

        public Builder withLastLogin(ZonedDateTime lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }

        public Builder withIsActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public UserModel build() {
            return new UserModel(userId, username, email, hashedPassword, roles, lastLogin, isActive);
        }
    }
}
