import com.mchange.v2.beans.swing.TestBean;
import com.reminder.demo.service.ReminderService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public ReminderService reminderService() {
        return Mockito.mock(ReminderService.class);
    }


}
