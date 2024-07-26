package org.example.example2.repository;

import org.example.example2.entity.contents.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserTest, Long> {
}