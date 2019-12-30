package com.infra.infra.services.user;

import com.infra.infra.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
