package com.infodev.auth.application.infrastructure.persistence.hibernate.repository;

import com.infodev.auth.application.domain.model.account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
