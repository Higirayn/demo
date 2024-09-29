package controllers;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/api/v1/reminder")
public class ReminderController {

    public void test1() {
        System.out.println("12314");
    }

}
