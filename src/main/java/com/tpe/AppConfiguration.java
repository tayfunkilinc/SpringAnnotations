package com.tpe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

@Configuration // springe bu classta yapilandirma ayarlari verilecek
@ComponentScan("com.tpe") //bu yolda bulunan tum componentlari(classlari) tarar - bu pathin bulundugu alani tarar buraya yol yazilmassa AppCongiguration nerde ise o yolda bulunan dosyalari tarar
//default path: AppConfiguration classinin icinde bulundugu path tanimlidir.
@PropertySource("classpath:application.properties")  //properties bir dosya uzantisi
//application.properties dosyasındaki bilgilerin okunmasını sağlar
public class AppConfiguration {

    //Springe ait bir interface, bean oluşturmadan enjekte edilebilir,
    // PropertySourcedaki dosyanın içindeki özelliklere(propertylere)
    // ve hatta uygulamanın çalıştığı ortam değişkenlerine
    // erişmemizi sağlayan metodlar içerir.
    @Autowired
    private Environment environment; //ortam cevre degiskenlerine ulasarak kullanmamizi saglar (ornegin properties)

    @Bean // bizim yazmadigimiz ucuncu parti classtan bean olusturulumasini saglar
    public Random random(){
        return new Random();
    }

    @Bean //bizim yazmadigimiz ucuncu parti classtan bean olusturulumasini saglar
    public Scanner scanner(){
        return new Scanner(System.in);
    }

    //properties dosyasindan degerlerin tamamini cekmek icin valeu anotasyonu altarnatifi
    //value anotasyonu ile yaptığımız işlemi Environment ve Properties Classı ile de yapabiliriz.
    @Bean //obje uretip bize veriyor
    public Properties properties(){
        Properties properties = new Properties();
        //properties.put("mymail","techproed@gmail.com"); //bunun yerine
        properties.put("mymail",environment.getProperty("eposta")); //bunu kullandim
        properties.put("myphone",environment.getProperty("phone"));
        properties.put("password", environment.getProperty("password.admin"));
        return properties;
    }

}
