package com.infra.infra.services.session;

import com.infra.infra.models.Session;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Session,Long> {
}
