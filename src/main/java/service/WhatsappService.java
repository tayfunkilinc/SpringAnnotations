package service;

import domain.Message;
import repository.Repository;

public class WhatsappService implements MessageService{

    private  Repository repository;

    public WhatsappService(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void sendMessage(Message message) {
        System.out.println("Mesajiniz Whatsapp Ile Gonderiliyor... : " + message.getBody());
    }

    @Override
    public void saveMessage(Message message) {

    }
}
