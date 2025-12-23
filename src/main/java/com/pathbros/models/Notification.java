package com.pathbros.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notificationId;

    private String NotificationTitle;

    @Column(nullable = false)
    private String notificationMessage;

    private boolean isRead=false;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime notificationOn;

    @ManyToOne
    private User user;

    @ManyToOne
    private Company company;
}
