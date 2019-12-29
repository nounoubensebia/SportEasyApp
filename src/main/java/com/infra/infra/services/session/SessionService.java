package com.infra.infra.services.session;



import com.infra.infra.models.Session;

import java.util.List;

public interface SessionService {

    public List<Session> getAll();
    public Session getById(long id);
    public void create(Session session);
    public void update(Session session);
    public void delete(Session session);

}
