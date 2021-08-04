package com.spring.social.security.jwt;

public class JwtLogin {

    private String email;

    private String password;

    public JwtLogin() {
    }

    public JwtLogin(String email, String password) {
        this.email = email;
        this.password = password;
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
}
