package com.infra.infra.services.inscription;

import com.infra.infra.models.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface InscriptionRepository extends JpaRepository<Inscription,Long> {
    @Modifying
    @Query("DELETE FROM Inscription WHERE id = ?1")
    void delete(Long entityId);
}
