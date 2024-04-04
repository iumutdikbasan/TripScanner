package com.iumutdikbasan.tripSearch.repository;

import com.iumutdikbasan.tripSearch.model.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public boolean existsUserByEmail(String email);
}
