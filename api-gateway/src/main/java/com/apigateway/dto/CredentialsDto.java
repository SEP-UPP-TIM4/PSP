package com.apigateway.dto;

public class CredentialsDto {

    String username;

    String password;

    public CredentialsDto() {
    }

    public CredentialsDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "CredentialsDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
