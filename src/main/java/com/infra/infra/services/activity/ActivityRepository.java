package com.infra.infra.services.activity;

import com.infra.infra.models.Activity;
import org.springframework.data.repository.CrudRepository;

public interface ActivityRepository extends CrudRepository<Activity,Long> {
}
