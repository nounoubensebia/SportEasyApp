package com.infra.infra.services.session;

import com.infra.infra.models.Inscription;
import com.infra.infra.models.Session;
import com.infra.infra.services.inscription.InscriptionService;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    private SessionRepository sessionRepository;

    private InscriptionService inscriptionService;

    @Autowired
    public void setSessionRepository(SessionRepository sessionRepository)
    {
        this.sessionRepository =sessionRepository;
    }

    @Autowired
    public void setInscriptionService(InscriptionService inscriptionService)
    {
        this.inscriptionService = inscriptionService;
    }

    @Override
    public List<Session> getAll() {
        return IterableUtils.toList(sessionRepository.findAll());
    }

    @Override
    public Session getById(long id) {
        if (sessionRepository.findById(id).isPresent())
            return sessionRepository.findById(id).get();
        else
            return null;
    }

    @Override
    public void create(Session session) {
        sessionRepository.save(session);
    }

    @Override
    public void update(Session session) {
        sessionRepository.save(session);
    }

    @Override
    public void delete(Session session) {

        for (Inscription inscription:session.getInscriptions())
        {
            inscriptionService.delete(inscription);
        }

        sessionRepository.delete(session);
    }

}
