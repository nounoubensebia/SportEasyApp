package com.infra.infra.services.inscription;

import com.infra.infra.models.Activity;
import com.infra.infra.models.Inscription;
import com.infra.infra.models.Session;
import com.infra.infra.models.User;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscriptionServiceImpl implements InscriptionService {

    private InscriptionRepository inscriptionRepository;

    @Autowired
    public void setInscriptionRepository(InscriptionRepository inscriptionRepository)
    {
        this.inscriptionRepository = inscriptionRepository;
    }

    @Override
    public List<Inscription> getAll() {
        return IterableUtils.toList(inscriptionRepository.findAll());
    }

    @Override
    public Inscription getById(long id) {
        if (inscriptionRepository.findById(id).isPresent())
            return inscriptionRepository.findById(id).get();
        else
            return null;
    }

    @Override
    public Inscription create(Inscription inscription) {
        return inscriptionRepository.save(inscription);
    }

    @Override
    public void update(Inscription inscription) {
        inscriptionRepository.save(inscription);
    }

    @Override
    public void delete(Inscription inscription) {
        inscriptionRepository.delete(inscription);
    }

    @Override
    public PossibleRegistration isRegistrationPossible(User user, Session session, boolean titular) {
        //check if capacity reached
        if (session.atFullCapacity(titular))
            return PossibleRegistration.FULL_CAPACITY;

        Activity activity = session.getActivity();
        int registrationType = user.getRegistrationType(activity);

        if (registrationType == User.NOT_REGISTERED_TO_ACTIVITY_AT_ALL)
        {
            if(user.getNumberOfRegistrationsByGroup(session.getActivity().getGroupe())==2)
            {
                return PossibleRegistration.LIMIT_PER_GROUP_REACHED;
            }
            else
            {
                return PossibleRegistration.REGISTRATION_POSSIBLE;
            }
        }

        if (registrationType == User.REGISTERED_TO_ACTIVITY_AS_TITULAR_BUT_NOT_FOR_NEXT_WEEK)
        {
            if (!titular)
                return PossibleRegistration.REGISTRATION_POSSIBLE;
            else
                return PossibleRegistration.ALREADY_REGISTERED;
        }

        if (registrationType == User.REGISTERED_TO_ACTIVITY_AS_OPTIONAL_AND_TITULAR ||
                registrationType == User.REGISTERED_TO_ACTIVITY_AS_OPTIONAL_ONLY ||
                registrationType == User.REGISTERED_TO_ACTIVITY_AS_TITULAR_ONLY)
        {
            return PossibleRegistration.ALREADY_REGISTERED;
        }

        return null;
    }

    //TODO Implement
    @Override
    public void deleteLastOptionalRegistration() {

    }

}
