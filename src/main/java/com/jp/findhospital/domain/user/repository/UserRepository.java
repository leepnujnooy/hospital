package com.jp.findhospital.domain.user.repository;

import com.jp.findhospital.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}