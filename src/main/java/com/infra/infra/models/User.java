package com.infra.infra.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private Date birthDate;
    private int gender;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user",
            targetEntity = Inscription.class,
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Inscription> inscriptions;

    public User(String firstName, String lastName, Date birthDate, int gender, String email, String password, List<Inscription> inscriptions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.inscriptions = inscriptions;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public int getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }
}
