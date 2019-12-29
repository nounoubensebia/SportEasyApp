package com.infra.infra.models;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    private boolean titular;

    private LocalDateTime inscriptionDate;

    public Inscription(User user, Session session, boolean titular, LocalDateTime inscriptionDate) {
        this.user = user;
        this.session = session;
        this.titular = titular;
        this.inscriptionDate = inscriptionDate;
    }

    public Inscription() {
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public boolean isTitular() {
        return titular;
    }

    public void setTitular(boolean titular) {
        this.titular = titular;
    }

    public LocalDateTime getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate(LocalDateTime inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }
}
