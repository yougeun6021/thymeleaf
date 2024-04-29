package com.amudy.security.domain;

import lombok.Getter;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
public class PersistentLogins {

    @Id
    @Column(length = 64)
    private String series;

    @Column(nullable = false, length = 64)
    private String username;

    @Column(nullable = false, length = 64)
    private String token;

    @Column(nullable = false, length = 64)
    private Date lastUsed;

    protected PersistentLogins() {

    }

    private PersistentLogins(PersistentRememberMeToken token){
        this.series = token.getSeries();
        this.username = token.getUsername();
        this.token = token.getTokenValue();
        this.lastUsed = token.getDate();
    }

    public static PersistentLogins from(PersistentRememberMeToken token){
        return new PersistentLogins(token);
    }

    public void updateToken(String token,Date lastUsed){
        this.token = token;
        this.lastUsed = lastUsed;
    }

}
