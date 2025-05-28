package com.cet.collaborative_editing_tool.dto;

import com.cet.collaborative_editing_tool.enums.ERoles;
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private ERoles role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ERoles getRole() {
        return role;
    }

    public void setRole(ERoles role) {
        this.role = role;
    }
}
