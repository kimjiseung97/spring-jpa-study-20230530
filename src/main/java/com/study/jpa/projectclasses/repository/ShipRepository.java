package com.study.jpa.projectclasses.repository;

import com.study.jpa.projectclasses.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<Ship, Integer> {
}
