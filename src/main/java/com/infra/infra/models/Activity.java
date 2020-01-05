package com.infra.infra.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String activityName;

    @ManyToOne
    @JoinColumn(name = "groupe_id")
    private Groupe groupe;
    

    @OneToMany(mappedBy = "activity",
            targetEntity = Session.class,
            fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Session> sessions;

    public Activity(String activityName, Groupe groupe, List<Session> sessions) {
        this.activityName = activityName;
        this.groupe = groupe;
        this.sessions = sessions;
    }

    public Activity() {
    }

    public long getId() {
        return id;
    }

    public String getActivityName() {
        return activityName;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public boolean equals(Object obj) {
        if((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        Activity other = (Activity)obj;
        return other.id==this.id;
    }
}
