package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.service.MessageSender;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final MessageSender messageSender;
    private final TelegramBot telegramBot;
    private final String WELCOME_MESSAGE = "Привет Меченый! Короче, я тебя спас и в благородства играть не буду, выполни для меня пару заданий, и мы в расчете! ";
    private final Pattern INCOMING_MESSAGE_PATTERN = Pattern.compile("(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)");



    public TelegramBotUpdatesListener(MessageSender messageSender, TelegramBot telegramBot) {
        this.messageSender = messageSender;
        this.telegramBot = telegramBot;
    }


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Обновление обработки: {}", update);

            long chatId = update.message().chat().id();
            String message = update.message().text();

            if ("/start".equals(message)) {
                messageSender.send(chatId, WELCOME_MESSAGE);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
