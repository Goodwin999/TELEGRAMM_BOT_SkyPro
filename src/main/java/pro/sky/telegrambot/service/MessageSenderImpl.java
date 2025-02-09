package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderImpl implements MessageSender {
    private final Logger logger = LoggerFactory.getLogger(MessageSenderImpl.class);

    private final TelegramBot telegramBot;

    public MessageSenderImpl(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public void send(long chatId, String messages) {
        SendMessage message = new SendMessage(chatId, messages);

        SendResponse response = telegramBot.execute(message);
        if (response.isOk()) {
            logger.info("Сообщение для ChatId {} и текстовое сообщение {} успешно отправлены.", chatId, message);
        } else {
logger.error("Произошла ошибка при отправке сообщения для ChatId {} и текстовое сообщение {}", chatId, message);


        }
    }


    }

