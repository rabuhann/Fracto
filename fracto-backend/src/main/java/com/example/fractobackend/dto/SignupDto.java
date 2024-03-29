package com.example.fractobackend.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class SignupDto {
    private String userName;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> userType;
    public SignupDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<? extends GrantedAuthority> getUserType() {
        return userType;
    }

    public void setUserType(Collection<? extends GrantedAuthority> userType) {
        this.userType = userType;
    }
}
