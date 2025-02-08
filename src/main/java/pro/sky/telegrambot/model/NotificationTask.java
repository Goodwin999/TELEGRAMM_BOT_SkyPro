package pro.sky.telegrambot.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "notification_task")
public class NotificationTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private String messageText;
    private LocalDateTime notificationDateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public LocalDateTime getNotificationDateTime() {
        return notificationDateTime;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return Objects.equals(id, that.id) && Objects.equals(chatId, that.chatId) && Objects.equals(messageText, that.messageText) && Objects.equals(notificationDateTime, that.notificationDateTime);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, messageText, notificationDateTime);
    }
    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", номер чата=" + chatId +
                ", текст сообщения='" + messageText + '\'' +
                ", уведомление для даты и времени=" + notificationDateTime +
                '}';
    }

}