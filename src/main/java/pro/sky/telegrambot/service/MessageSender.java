package pro.sky.telegrambot.service;

public interface MessageSender {
    void send (long chatId, String message);

}
