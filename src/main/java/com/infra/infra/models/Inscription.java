package com.infra.infra.models;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

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

    private LocalDateTime desincriptionDate;

    public Inscription(User user, Session session, boolean titular, LocalDateTime inscriptionDate, LocalDateTime desincriptionDate) {
        this.user = user;
        this.session = session;
        this.titular = titular;
        this.inscriptionDate = inscriptionDate;
        this.desincriptionDate = desincriptionDate;
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

    public LocalDateTime getDesincriptionDate() {
        return desincriptionDate;
    }

    public void setDesincriptionDate(LocalDateTime desincriptionDate) {
        this.desincriptionDate = desincriptionDate;
    }

    // check if inscription is currently active ie if optional not expired and if titular not unregistered
    public boolean isActive()
    {
        if (titular) {
            LocalDateTime desincriptionDate = getDesincriptionDate();
            if (desincriptionDate == null || desincriptionDate.isBefore(LocalDateTime.now())) {
                return true;
            } else {
                return false;
            }
        }
        else
        {
            return isOptionalActive();
        }

    }

    private boolean isOptionalActive()
    {
        LocalDateTime currentDate = LocalDateTime.now();
        return currentDate.isBefore(inscriptionDate);
    }
}
