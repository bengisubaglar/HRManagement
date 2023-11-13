package com.hr.management.sonik.repository;

import com.hr.management.sonik.entity.HumanResources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HumanResourcesRepository extends JpaRepository<HumanResources,Long> {

    Optional<HumanResources> findByEmail(String email);
}
