package com.pathbros.repositories;

import com.pathbros.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByUserEmail(String eMail);

    List<User> findByUserCityAndUserIsActiveTrue(String userCity);

    Optional<User> findByUserEmailAndUserIsActiveTrue(String eMail);


}
