package com.infra.infra.models;

import com.infra.infra.BCryptManagerUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private Date birthDate;
    private int gender;
    private String email;
    private String password;
    private boolean isAdmin;

    public static int REGISTERED_TO_ACTIVITY_AS_TITULAR_ONLY = 1;

    public static int REGISTERED_TO_ACTIVITY_AS_OPTIONAL_ONLY = 2;

    public static int REGISTERED_TO_ACTIVITY_AS_OPTIONAL_AND_TITULAR = 3;

    public static int NOT_REGISTERED_TO_ACTIVITY_AT_ALL = 0;

    public static int REGISTERED_TO_ACTIVITY_AS_TITULAR_BUT_NOT_FOR_NEXT_WEEK = -1;

    @OneToMany(mappedBy = "user",
            targetEntity = Inscription.class,
            fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Inscription> inscriptions;

    public User(String firstName, String lastName, Date birthDate, int gender, String email, String password,
                List<Inscription> inscriptions,boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.inscriptions = inscriptions;
        this.isAdmin = isAdmin;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList("user");
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
        if (!password.isEmpty()) {
            this.password = BCryptManagerUtil.passwordencoder().encode(password);
        }
    }

    public void setPasswordRaw(String password) {
        this.password = password;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public int getRegistrationType(Activity activity)
    {
        ArrayList<Inscription> inscriptions = new ArrayList<>(this.getInscriptions());
        CollectionUtils.filter(inscriptions, new Predicate<Inscription>() {
            @Override
            public boolean evaluate(Inscription inscription) {
                return inscription.getSession().getActivity().equals(activity);
            }
        });
        boolean titular = false;
        boolean optional = false;
        boolean notThisWeek = false;
        for (Inscription inscription : inscriptions)
        {
            if (inscription.isTitular())
            {
                if ((inscription.getDesincriptionDate()==null||
                        inscription.getDesincriptionDate().isBefore(LocalDateTime.now())))
                {
                    titular = true;
                    break;
                }
                else
                {
                    titular =true;
                    notThisWeek = true;
                    break;
                }
            }
            else
            {
                if (inscription.getInscriptionDate().isAfter(LocalDateTime.now()))
                {
                    optional = true;
                }
            }
        }
        if (titular&&optional)
            return REGISTERED_TO_ACTIVITY_AS_OPTIONAL_AND_TITULAR;
        if (optional)
            return REGISTERED_TO_ACTIVITY_AS_OPTIONAL_ONLY;
        if (titular&&!notThisWeek)
            return REGISTERED_TO_ACTIVITY_AS_TITULAR_ONLY;
        if (titular&&notThisWeek)
            return REGISTERED_TO_ACTIVITY_AS_TITULAR_BUT_NOT_FOR_NEXT_WEEK;

        return NOT_REGISTERED_TO_ACTIVITY_AT_ALL;
    }

    public int getNumberOfRegistrationsByGroup(Groupe groupe)
    {
        ArrayList<Inscription> activeInscriptions = new ArrayList<>(getInscriptions());
        CollectionUtils.filter(activeInscriptions, new Predicate<Inscription>() {
            @Override
            public boolean evaluate(Inscription inscription) {
                return inscription.isActive();
            }
        });

        int s = 0;

        for (Inscription inscription:activeInscriptions)
        {
            if (inscription.getSession().getActivity().getGroupe().getId()==groupe.getId())
            {
                s++;
            }
        }

        return s;
    }
}
