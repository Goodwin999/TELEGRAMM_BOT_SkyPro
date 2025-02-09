package pro.sky.telegrambot.job;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;
import pro.sky.telegrambot.service.MessageSender;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class NotificationTaskJob {
    private final Logger logger = LoggerFactory.getLogger(NotificationTaskJob.class);

    private final NotificationTaskRepository notificationTaskRepository;
    private final MessageSender messageSender;

    public NotificationTaskJob(NotificationTaskRepository notificationTaskRepository, MessageSender messageSender) {
        this.notificationTaskRepository = notificationTaskRepository;
        this.messageSender = messageSender;
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void sentNotifications() {
        LocalDateTime currentDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
logger.info("Начать задание на дату и время {} ", currentDateTime);
        List<NotificationTask> notificationTasks =
                notificationTaskRepository.findAllByNotificationDateTime(currentDateTime);
logger.info("Найдено {} задач уведомлений ", notificationTasks.size());
        for (NotificationTask notificationTask : notificationTasks) {
            messageSender.send(
                    notificationTask.getChatId(),
                    notificationTask.getMessage()
            );
            logger.info("Успешно отправлено напоминание о задаче с идентификатором {}", notificationTask.getId());
        }
        logger.info("Работа завершена");


    }
}
