package com.example.MouseNest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mail.smtp")
public class MailProperties {
    private String username;
    private String password;
    private boolean auth;
    private boolean starttls;
    private String host;
    private Integer port;
    private String trust;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public boolean isStarttls() {
        return starttls;
    }

    public void setStarttls(boolean starttls) {
        this.starttls = starttls;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getTrust() {
        return trust;
    }

    public void setTrust(String trust) {
        this.trust = trust;
    }
}
