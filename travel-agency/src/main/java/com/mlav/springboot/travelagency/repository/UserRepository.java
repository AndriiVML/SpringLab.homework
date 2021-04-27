package com.mlav.springboot.travelagency.repository;

import com.mlav.springboot.travelagency.dto.UserDto;
import com.mlav.springboot.travelagency.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    default int getCount() {
        return findAll().size();
    }

//    Optional<User> findByLogin(String login);
    Optional<User> findByAccount_Login(String login);


    Optional<User> findByEmail(String email);

//    void deleteByAccount_Login(String login);


}
