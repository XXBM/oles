package com.oles.repository;


import com.oles.JpaRepository.MyRepository;
import com.oles.domain.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MyRepository<User, Long> {
    User findByUserName(String username);
}
