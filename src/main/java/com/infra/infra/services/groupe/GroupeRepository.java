package com.infra.infra.services.groupe;

import com.infra.infra.models.Groupe;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GroupeRepository extends CrudRepository<Groupe,Long> {
    @Modifying
    @Query("DELETE FROM Groupe WHERE id = ?1")
    void delete(Long entityId);
}
