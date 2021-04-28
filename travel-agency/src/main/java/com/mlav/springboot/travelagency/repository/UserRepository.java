package com.mlav.springboot.travelagency.repository;

import com.mlav.springboot.travelagency.dto.UserDto;
import com.mlav.springboot.travelagency.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    default int getCount() {
        return findAll().size();
    }

    Optional<User> findByAccount_Login(String login);


    Optional<User> findByEmail(String email);

}
