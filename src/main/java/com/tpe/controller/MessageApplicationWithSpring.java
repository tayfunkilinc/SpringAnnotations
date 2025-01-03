package com.tpe.controller;

import com.tpe.AppConfiguration;
import com.tpe.domain.Message;
import com.tpe.repository.DbRepository;
import com.tpe.repository.Repository;
import com.tpe.service.MessageService;
import com.tpe.service.SlackService;
import com.tpe.service.SmsService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MessageApplicationWithSpring {
    public static void main(String[] args) {

        Message message = new Message();
        message.setBody("Welcome SPRING:");


        //config(AppConfiguration) classını okur ve componentscan ile componentları ve beanleri tarar ve her birinden
        //sadece 1 tane Spring bean oluşturur ve contexte atar ve hazır olarak bekletir.
        //bean istendiğinde gerekliyse içine bağımlılığını da enjekte ederek gönderir.
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);


//        //mesaji SMS ile gonderelim : smsservice objesi gerekli
//        MessageService service1 = context.getBean(SmsService.class);
//        service1.sendMessage(message);
//
//        MessageService service2 = context.getBean("smsService",MessageService.class); //smsService, slack_service gibi ayni isimli
//        service2.sendMessage(message);
//        //getBean metoduna parametre olarak parentı verirsek ve birden fazla childı varsa
//        //beanin ismini de belirtmeliyiz.
//
//
//        //mesaji slack ile gonderelim
//        MessageService service3 = context.getBean("slack_service",SlackService.class);//smsService, slack_service gibi ayni isimli componet'ler hataya yol acar bu nedenle isimle kullanmak gerekir
//        service3.sendMessage(message);


        //eger talep ettiginiz bean bagimlilik gerektiriyorsa
        MessageService service4 = context.getBean(SmsService.class);
        service4.sendMessage(message);
        service4.saveMessage(message);
        //smsService i newlemedik
        //service repoya bağımlı ama biz enjekte etmedik
        //repo objesini de biz oluşturmadık
        //Spring SAĞOLSUN:)

        Repository repository = context.getBean(DbRepository.class);
        repository.save(message);




    }
}
