package service;

import domain.Message;

public interface MessageService {
    void sendMessage(Message message);
    void saveMessage(Message message);
}
