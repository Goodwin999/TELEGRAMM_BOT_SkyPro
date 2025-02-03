package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Проверяем, является ли текст сообщения командой /start
            if (update.message() != null && update.message().text().equals("/start")) {
                long chatId = update.message().chat().id();  // Получаем идентификатор чата
                // Отправляем приветственное сообщение в этот чат
                telegramBot.execute(new SendMessage(chatId, "А я думал это сова!"));
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
