package com.tpe;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // springe bu classta yapilandirma ayarlari verilecek
@ComponentScan("com.tpe") //bu yolda bulunan tum componentlari(classlari) tarar - bu pathin bulundugu alani tarar buraya yol yazilmassa AppCongiguration nerde ise o yolda bulunan dosyalari tarar
//default path: AppConfiguration classinin icinde bulundugu path tanimlidir.
public class AppConfiguration {


}
