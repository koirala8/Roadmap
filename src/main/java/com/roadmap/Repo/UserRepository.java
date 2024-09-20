package com.roadmap.Repo;

import com.roadmap.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPasswordAndRole(String username, String password, String role);
}
