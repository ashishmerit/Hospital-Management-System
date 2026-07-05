package com.hms.backend.dto.response;

public class AuthResponse {

    private String token;
    private String role;
    private String firstName;
    private String message;

    public AuthResponse() {
    }

    public AuthResponse(String token, String role, String firstName, String message) {
        this.token = token;
        this.role = role;
        this.firstName = firstName;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}