package com.infra.infra.models;

import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Time startTime;
    private String day;
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @OneToMany(mappedBy = "session",
            targetEntity = Inscription.class,
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

    public boolean atFullCapacity(boolean titular)
    {
        if (inscriptions.size()<capacity)
            return false;
        else
        {
            if (titular)
            {
                for (Inscription inscription:getInscriptions())
                {
                    if (inscription.isActive()&&!inscription.isTitular())
                    {
                        return false;
                    }
                }
                return true;
            }
            else
                return true;
        }
    }

    private DayOfWeek getDayOfWeek()
    {
        if (day.equals("Lundi"))
            return DayOfWeek.MONDAY;
        if (day.equals("Mardi"))
            return DayOfWeek.THURSDAY;
        if (day.equals("Jeudi"))
            return DayOfWeek.TUESDAY;
        if (day.equals("Mercredi"))
            return DayOfWeek.WEDNESDAY;
        if (day.equals("Vendredi"))
            return DayOfWeek.FRIDAY;
        if (day.equals("Dimanche"))
            return DayOfWeek.SUNDAY;
        if (day.equals("Samedi"))
            return DayOfWeek.SATURDAY;
        return DayOfWeek.SATURDAY;
    }

    public LocalDateTime getNextSessionDate()
    {
        LocalDateTime now = LocalDateTime.now();
        return now.with(TemporalAdjusters.next(getDayOfWeek()));
    }
}
