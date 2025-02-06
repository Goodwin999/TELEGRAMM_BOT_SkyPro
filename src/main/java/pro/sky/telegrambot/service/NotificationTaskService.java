package pro.sky.telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationTaskService {

    private final NotificationTaskRepository notificationTaskRepository;

    @Autowired
    public NotificationTaskService(NotificationTaskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
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
