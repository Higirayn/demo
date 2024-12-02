package com.reminder.demo.repository;

import com.reminder.demo.entity.Reminder;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    Optional<Reminder> findByIdAndUserId(Long id, Long userId);

    Page<Reminder> findByUserId(Long userId, Pageable pageable);


    @Query("SELECT r FROM Reminder r where  r.user.id = :userId AND " +
            "(LOWER(r.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(r.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "r.date = :searchDate)")
    Page<Reminder> searchByUserIdAndTerm(@Param("userId") Long userId,
                                         @Param("searchTerm") String searchTerm,
                                         @Param ("searchDate") LocalDateTime searchDate,
                                         Pageable pageable);


}
