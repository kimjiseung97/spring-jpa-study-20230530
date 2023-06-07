package com.study.jpa.projectclasses.repository;

import com.study.jpa.projectclasses.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

}
