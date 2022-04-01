package com.epico.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epico.auth.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
