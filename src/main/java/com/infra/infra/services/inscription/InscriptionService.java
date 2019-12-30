package com.infra.infra.services.inscription;

import com.infra.infra.models.Inscription;
import com.infra.infra.models.Session;
import com.infra.infra.models.User;

import java.time.LocalDateTime;
import java.util.List;

public interface InscriptionService {

    public static int REGISTRATION_POSSIBLE = 1;
    public static int FULL_CAPACITY = 0;
    public static int LIMIT_PER_GROUP_REACHED = -1;
    public static int ALREADY_REGISTERED = -2;

    public enum PossibleRegistration {
        REGISTRATION_POSSIBLE, FULL_CAPACITY, LIMIT_PER_GROUP_REACHED, ALREADY_REGISTERED
    }

    public List<Inscription> getAll();
    public Inscription getById(long id);
    public void create(Inscription inscription);
    public void update(Inscription inscription);
    public void delete(Inscription inscription);
    public PossibleRegistration isRegistrationPossible(User user, Session session, boolean titular);
    public void deleteLastOptionalRegistration();

}
