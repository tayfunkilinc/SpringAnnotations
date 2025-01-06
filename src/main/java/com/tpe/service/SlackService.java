package com.tpe.service;

import com.tpe.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

@Component ("slack_service")//bu annotation yoksa burdan bean uretilmez bunu istedigimiz yerlere yazmaliyiz
@Scope("prototype")
public class SlackService implements MessageService{

    //NOT: bir class baska bir class'in objesine ihtiyac duyarsa bagimlilik olarak eklemek gerek
    @Autowired //burda contex olmadigi icin enjekte etmemiz gerek
    private Random random;

    @Autowired
    private Scanner scanner;

    @Override
    public void sendMessage(Message message) {
        System.out.println("Mesajınız Slack ile gönderiliyor. Mesaj: "+message.getBody());
    }

    @Override
    public void saveMessage(Message message) {
        //random deger uretmek istiyorum
        //Random random = new Random(); // bunu bean ile yapacagiz
        System.out.println(random.nextInt(100)); //burda direk enjekte ettigimiz icin nesne olusturmadan random sayiyi uretti

        System.out.println("Spring Nasil: ");
        System.out.println(scanner.nextLine());

    }

    //=-=================
    //Altta verilen PostConstruct ve PreDestroy annotation,lari javax.annotation bagimliligi yardimi ile kullanilabilmektedir
    @PostConstruct // bu classtan bir obje uretilince bu method calisir
    public void postConstruct(){
        System.out.println("--------slack service objesi uretildi..");
    }
    @PreDestroy // burada obje yok edildikten hemen sonra bu methodu calistirir
    public void preDestroy(){
        System.out.println("--------slack service objesi imha edildi");
    }

    //-------------------------------properties----------------------------------------------

    @Value("${eposta}") //burda application.properties dosyasi icerisinden bu degeri al dedik
    private String email;

    @Value("${phone}") //burda application.properties dosyasi icerisinden bu degeri al dedik
    private String phone;

    public void printContact(){
        System.out.println("e-mail : " + this.email);
        System.out.println("phone  : " + this.phone);
    }

    //------ degiskenle degilde direk bir methotta toplayip bu sekilde kullanabiliriz
    @Autowired
    private Properties properties;

    public void getContact(){
        System.out.println("e-mail : " + properties.getProperty("mymail"));
        System.out.println("phone  : " + properties.getProperty("myphone"));
    }



}
