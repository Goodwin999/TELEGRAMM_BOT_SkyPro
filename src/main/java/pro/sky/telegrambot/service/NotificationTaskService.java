package pro.sky.telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NotificationTaskService {

    private final NotificationTaskRepository notificationTaskRepository;

    @Autowired
    public NotificationTaskService(NotificationTaskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
    }

    public String parseAndSaveNotification(String message) {
        Pattern pattern = Pattern.compile("(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            String dateTimeStr = matcher.group(1);
            String notificationMessage = matcher.group(3);

            LocalDateTime notificationDateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));

            NotificationTask notificationTask = new NotificationTask();
            notificationTask.setNotificationDateTime(notificationDateTime);
            notificationTask.setMessage(notificationMessage);

            notificationTaskRepository.save(notificationTask);
            return "Задача успешно добавлена!";
        } else {
            throw new IllegalArgumentException("Неверный формат сообщения!");
        }
    }

    public NotificationTask saveNotificationTask(NotificationTask notificationTask) {
        return notificationTaskRepository.save(notificationTask);
    }

    public Optional<NotificationTask> getNotificationTaskById(Long id) {
        return notificationTaskRepository.findById(id);
    }

    public List<NotificationTask> getAllNotificationTasks() {
        return notificationTaskRepository.findAll();
    }

    public void deleteNotificationTask(Long id) {
        notificationTaskRepository.deleteById(id);
    }
}
