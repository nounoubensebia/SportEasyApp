package com.infra.infra.services.session;

import com.infra.infra.models.Session;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    private SessionRepository sessionRepository;

    @Autowired
    public void setSessionRepository(SessionRepository sessionRepository)
    {
        this.sessionRepository =sessionRepository;
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
        sessionRepository.delete(session);
    }
}
