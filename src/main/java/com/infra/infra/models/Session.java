package com.infra.infra.models;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Time startTime;
    private String day;
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @OneToMany(mappedBy="session",
            targetEntity=Inscription.class,
            fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Inscription> inscriptions;

    public Session(Time startTime, String day, int capacity, Activity activity, List<Inscription> inscriptions) {
        this.startTime = startTime;
        this.day = day;
        this.capacity = capacity;
        this.activity = activity;
        this.inscriptions = inscriptions;
    }

    public Session() {
    }

    public long getId() {
        return id;
    }

    public Time getStartTime() {
        return startTime;
    }

    public String getDay() {
        return day;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
