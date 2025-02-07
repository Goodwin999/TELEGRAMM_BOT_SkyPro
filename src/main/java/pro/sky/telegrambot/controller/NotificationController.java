package pro.sky.telegrambot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.service.NotificationTaskService;


@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationTaskService notificationTaskService;

    // Эндпоинт для создания напоминания
    @PostMapping("/create")
    public String createNotification(@RequestParam Long chatId, @RequestParam String message) {
        return notificationTaskService.parseAndSaveNotification(chatId, message);
    }
}
