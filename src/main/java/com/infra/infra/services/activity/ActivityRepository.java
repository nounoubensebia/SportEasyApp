package com.infra.infra.services.activity;

import com.infra.infra.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ActivityRepository extends JpaRepository<Activity,Long> {
    @Modifying
    @Query("DELETE FROM Activity WHERE id = ?1")
    void delete(Long entityId);
}
