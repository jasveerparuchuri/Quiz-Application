package com.jasveer.quiz_service.repository;

import com.jasveer.quiz_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
