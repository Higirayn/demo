package com.reminder.demo.service.quartz;

import com.reminder.demo.entity.Reminder;
import com.reminder.demo.entity.User;
import com.reminder.demo.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReminderSheduler {

    @Autowired
    private ReminderService reminderService;

    @Autowired
    private JavaMailSender mailSender;

    @Scheduled(fixedRate = 1000)
    public void checkReminder() {
        List<Reminder> reminderList = reminderService.getReminderRepository().findAll();
        LocalDateTime now = LocalDateTime.now();
        for(Reminder reminder : reminderList) {
            if(reminder.getDate().withNano(0).equals(now.withNano(0)) && reminder.getUser() != null) {
                   User user = reminder.getUser();
                   sendMail(user.getEmail(), reminder.getTitle(), reminder.getDescription());
                System.out.println("Отправил");
            }
        }
    }

    public void sendMail(String to,
                         String subject,
                         String text) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("higiraynov2s@gmail.com");
        message.setTo(to);
        message.setText(text);
        message.setSubject(subject);

        mailSender.send(message);
    }

}
