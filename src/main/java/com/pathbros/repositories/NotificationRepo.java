package com.pathbros.repositories;

import com.pathbros.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification,Integer> {

    List<Notification> findByUser_UserEmailOrderByNotificationOnDesc(String email);

    List<Notification> findByCompany_CompanyEmailOrderByNotificationOnDesc(String email);


}
