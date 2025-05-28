package com.cet.collaborative_editing_tool.dto;

import com.cet.collaborative_editing_tool.enums.ERoles;

public class AuthenticationResponse {
    private String token;
    private Long userId;
    private ERoles role;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ERoles getRole() {
        return role;
    }

    public void setRole(ERoles role) {
        this.role = role;
    }
}
