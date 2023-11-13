package com.hr.management.sonik.repository;

import com.hr.management.sonik.entity.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseUserRepository extends JpaRepository<BaseUser,Long> {

}
