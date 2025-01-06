package com.tpe.controller;

import com.tpe.AppConfiguration;
import com.tpe.domain.Message;
import com.tpe.repository.DbRepository;
import com.tpe.repository.Repository;
import com.tpe.service.MessageService;
import com.tpe.service.SlackService;
import com.tpe.service.SmsService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

public class MessageApplicationWithSpring {
    public static void main(String[] args) {

        Message message = new Message();
        message.setBody("Welcome SPRING:");


//config(AppConfiguration) classını okur ve componentscan ile componentları ve beanleri tarar ve her birinden
        //sadece 1 tane Spring bean oluşturur ve contexte atar ve hazır olarak bekletir.
        //bean istendiğinde gerekliyse içine bağımlılığını da enjekte ederek gönderir.
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);




        //mesaji SMS ile gonderelim : smsservice objesi gerekli ama bu isi contex ile spring bize direk hazirlayip gondermis oldu
        MessageService service1 = context.getBean(SmsService.class); //newlemedik kedisi bize objeyi olusturup getirdi
        service1.sendMessage(message);

        MessageService service2 = context.getBean("smsService",MessageService.class); //smsService, slack_service gibi ayni isimli
        service2.sendMessage(message);
        //getBean metoduna parametre olarak parentı verirsek ve birden fazla childı varsa
        //beanin ismini de belirtmeliyiz.


        //mesaji slack ile gonderelim
        MessageService service3 = context.getBean("slack_service",SlackService.class);//smsService, slack_service gibi ayni isimli componet'ler hataya yol acar bu nedenle isimle kullanmak gerekir
        service3.sendMessage(message);


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

        //random bir degere ihtiyacim var  uretelim ve yazidrallim

        //Random random = new Random();  //artik bunu kullanmiyorum kendi urettigim Bean'i kullanacagim
        Random random = context.getBean(Random.class);
        System.out.println("Random Deger: " + random.nextInt(100));

        MessageService service5 = context.getBean(SlackService.class);
        service5.saveMessage(message);

        //context objemiz varsa beani getBean ile isteyip kullanabiliriz
        //diger classlarda ise enjecte ederek ayni beani kullanabiliriz

        MessageService service6 = context.getBean(SlackService.class);
        MessageService service7 = context.getBean(SlackService.class);
        if(service6==service7){
            System.out.println("Ayni Objeler");
            System.out.println(service6);
            System.out.println(service7);
        }else {
            System.out.println("Farkli Objeler");
            System.out.println(service6);
            System.out.println(service7);
        }
        //Beans Scope -- Singleton iken
//        Ayni Objeler
//        com.tpe.service.SlackService@7c7a06ec
//        com.tpe.service.SlackService@7c7a06ec

        //Beans Scope -- Prototype iken
//        Farkli Objeler
//        com.tpe.service.SlackService@36ebc363
//        com.tpe.service.SlackService@45752059


        //----------------------------------

        SlackService service8 = context.getBean(SlackService.class); //burda printContact() methodu sadece SlackService ait oldugu icin parent MessageService'den referans alamadim
        service8.printContact();
        System.out.println("-----------------------------");
        service8.getContact();

        //----------------------------------






        //eger prototype kullanilir varsayilan degistirilirse kaynaklarin yonetiminide biz yapmaliyiz
        context.close(); // bunu varsayilan singleton durumunda spring yapiyordu artik kendimiz yapmaliyiz

        //HATA
        /*SmsService service = context.getBean(SmsService.class);
        service.sendMessage(message); //burda context kapatildigi icin artik kullanamam hata verir*/




    }
}
