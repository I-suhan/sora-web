package com.suhan.platform.repository;

import com.suhan.platform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(value = "soraTransactionManager")
public interface UserRepository extends JpaRepository<User, String> {
}
