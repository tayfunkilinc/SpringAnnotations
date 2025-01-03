package com.tpe.service;

import com.tpe.domain.Message;
import org.springframework.stereotype.Component;

@Component ("slack_service")//bu annotation yoksa burdan bean uretilmez bunu istedigimiz yerlere yazmaliyiz
public class SlackService implements MessageService{

    @Override
    public void sendMessage(Message message) {
        System.out.println("Mesajınız Slack ile gönderiliyor. Mesaj: "+message.getBody());
    }

    @Override
    public void saveMessage(Message message) {

    }
}
