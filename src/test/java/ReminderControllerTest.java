//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.reminder.demo.DemoApplication;
//import com.reminder.demo.controller.ReminderController;
//import com.reminder.demo.entity.Reminder;
//import com.reminder.demo.service.ReminderService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(ReminderController.class)
//@AutoConfigureMockMvc
//
//public class ReminderControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ReminderService reminderService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testAddReminder() throws Exception {
//        Long userId = 1L;
//
//        Reminder reminder = new Reminder();
//        reminder.setTitle("New Reminder");
//        reminder.setDescription("Test reminder desc");
//
//        Reminder savedReminder = new Reminder();
//        savedReminder.setId(1L);
//        savedReminder.setTitle("New Reminder");
//        savedReminder.setDescription("Test reminder desc");
//        when(reminderService.addReminderToUser(userId, reminder)).thenReturn(savedReminder);
//
//         mockMvc.perform(post("/api/users/{userId}/reminders", userId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(reminder)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.title").value("New Reminder"))
//                .andExpect(jsonPath("$.description").value("Test reminder desc"));
//
//        verify(reminderService, times(1)).addReminderToUser(userId, reminder);
//    }
//
//}
