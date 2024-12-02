import com.reminder.demo.entity.Reminder;
import com.reminder.demo.entity.User;
import com.reminder.demo.repository.ReminderRepository;
import com.reminder.demo.repository.UserRepository;
import com.reminder.demo.service.ReminderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReminderServiceTest {

    @Mock
    private ReminderRepository reminderRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ReminderService reminderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchReminders() {
        Long userId = 1L;
        String searchTerm = "Meeting";
        LocalDateTime dateTime = LocalDateTime.now();
        Pageable pageable = PageRequest.of(0, 10);
        User user = new User();
        user.setId(userId);

        Page<Reminder> page = new PageImpl<>(Collections.emptyList());
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(reminderRepository.searchByUserIdAndTerm(userId, searchTerm, dateTime, pageable)).thenReturn(page);

        Page<Reminder> result= reminderService.searchReminders(userId, searchTerm, dateTime, pageable);
        verify(userRepository, times(1)).findById(userId);
        verify(reminderRepository, times(1)).searchByUserIdAndTerm(userId, searchTerm, dateTime, pageable);

        assertEquals(page, result);

    }

    @Test
    public void testAddReminderToUser() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        Reminder reminder = new Reminder();
        reminder.setTitle("Привет");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(reminderRepository.save(any(Reminder.class))).thenReturn(reminder);

        Reminder result = reminderService.addReminderToUser(userId, reminder);
        verify(userRepository, times(1)).findById(userId);
        verify(reminderRepository, times(1)).save(reminder);

        assertEquals("Привет", result.getTitle());
        assertEquals(user, result.getUser());
    }

    @Test
    public void testDeleteReminder() {
        Long userId = 1L;
        Long reminderId = 2L;

        User user = new User();
        user.setId(userId);

        Reminder reminder = new Reminder();
        reminder.setId(reminderId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(reminderRepository.findByIdAndUserId(reminderId, userId)).thenReturn(Optional.of(reminder));
        reminderService.deleteReminder(userId, reminderId);

        verify(reminderRepository, times(1)).delete(reminder);
    }

}
