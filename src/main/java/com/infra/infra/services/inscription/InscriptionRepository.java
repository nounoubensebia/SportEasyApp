package com.infra.infra.services.inscription;

import com.infra.infra.models.Inscription;
import org.springframework.data.repository.CrudRepository;

public interface InscriptionRepository extends CrudRepository<Inscription,Long> {
}
