package com.infra.infra.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
public class Groupe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String groupName;

    @OneToMany(mappedBy = "groupe",
            targetEntity = Activity.class,
            fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Activity> activities;

    public Groupe(String groupName, List<Activity> activities) {
        this.groupName = groupName;
        this.activities = activities;
    }

    public Groupe() {
    }

    public long getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
