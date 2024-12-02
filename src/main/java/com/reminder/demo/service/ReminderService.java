package com.reminder.demo.service;

import com.reminder.demo.entity.Reminder;
import com.reminder.demo.entity.User;
import com.reminder.demo.repository.ReminderRepository;
import com.reminder.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReminderService {


    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ReminderRepository getReminderRepository() {
        return reminderRepository;
    }

    public void setReminderRepository(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Autowired
    private ReminderRepository reminderRepository;

    @Transactional
    @Autowired
    private UserRepository userRepository;

    public Reminder addReminderToUser(Long userId, Reminder reminder) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        reminder.setUser(user);

        return reminderRepository.save(reminder);

    }

    public Page<Reminder> searchReminders(
            Long userId,
            String searchTerm,
            LocalDateTime dateTime,
            Pageable pageable)
    {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );

        return reminderRepository.searchByUserIdAndTerm(userId, searchTerm,dateTime, pageable);

    }


    public Page<Reminder> getAllRemindersByUser(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );

        return reminderRepository.findByUserId(user.getId(), pageable);
    }


    public Reminder updateReminder(Long userId, Long reminderId, Reminder reminder) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );

        Reminder reminder1 = reminderRepository.findByIdAndUserId(reminderId, userId).orElseThrow(
                () -> new IllegalArgumentException("Reminder not found")
        );

        reminder1.setTitle(reminder.getTitle());
        reminder1.setDescription(reminder.getDescription());
        reminder1.setDate(reminder.getDate());

        return reminderRepository.save(reminder1);
    }

    public void deleteReminder(Long userId, Long reminderId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );

        Reminder reminder = reminderRepository.findByIdAndUserId(reminderId, userId).orElseThrow(
                () -> new IllegalArgumentException("Reminder not found")
        );
        reminderRepository.delete(reminder);
    }

}
