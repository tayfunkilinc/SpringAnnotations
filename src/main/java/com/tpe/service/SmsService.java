package com.tpe.service;

import com.tpe.domain.Message;
import com.tpe.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*
* 1. Constructor Injection (Yapıcı Yöntem ile Enjeksiyon)
Bu yöntemde, bağımlılıklar sınıfın constructor (yapıcı) yöntemi aracılığıyla sağlanır.

Spring, bağımlılıkları belirlenen constructor üzerinden enjekte eder.
Sınıfın bağımlılıklarını zorunlu kılmak için idealdir.

Bağımlılıklar zorunlu olduğundan, bağımlılıklar eksik olduğunda kod derleme aşamasında hata verir.
Sınıfın bağımlılıkları net bir şekilde görülür.
2. Setter Injection (Setter Yöntem ile Enjeksiyon)
Bağımlılıklar, setter metotları aracılığıyla enjekte edilir.

Spring, bağımlılıkları setter metotları aracılığıyla sağlar.

Bağımlılıkların isteğe bağlı olması durumunda kullanışlıdır.
Bağımlılıklar çalışma zamanında değiştirilebilir.

Bağımlılıklar zorunlu değilmiş gibi görünebilir ve yanlış konfigürasyonlara neden olabilir.
3. Field Injection (Alan Üzerinden Enjeksiyon)
Bağımlılıklar doğrudan sınıfın alanlarına enjekte edilir.

@Autowired anotasyonu, alanın üzerine doğrudan eklenir.
Spring, bağımlılığı doğrudan alan seviyesinde enjekte eder.

Kısa ve okunması kolaydır.
Kod daha az karmaşık görünür.

Alanlar genellikle private olduğundan, test etmek zordur (Mocking araçları gerekebilir).
Bağımlılıkları açık bir şekilde görme olanağı azalır.

Spring'de modern projelerde genellikle Constructor Injection tercih edilir çünkü:
Bağımlılıklar zorunlu hale gelir.
Nesneler final anahtar kelimesiyle tanımlanarak değişmez hale getirilebilir.
Test edilebilirlik daha kolaydır.*/




//bu classın objeleri Spring tarafından oluşturulur ve yönetilir ve bu objelere Spring Bean adı verilir.
//@Component // bir class objelerinin spring tarafindan uretilip yonetilmesi icin bu Annotation kullanilir
@Component
public class SmsService implements MessageService {

    //-------------------------------------------------
    //1- filed injection kullaniyoruz burda
    /*private Repository repo;
    @Autowired //burda normade DI yapmam gerekirdi burda otomatik bagimliligin otomatik enjekte edilmesini sagliyor
    @Qualifier("fileRepository") //talep edilen objenin birden fazla bean'i varsa burda hangisini istedigimizi belirtebiliriz
    */

   // -------------------------------------------------


   //-------------------------------------------------
    //2- setter injection kullanimi
    /*
    private Repository repo;
    @Autowired //burdada setter'i enjecte ederek repoyu kullanima actik
    @Qualifier("fileRepository")
    public void setRepo(Repository repo) {this.repo = repo;}*/

    //-------------------------------------------------

    //3-contructor injection : daha guvenli, daha anlasilir, test etmesi kolaydir
    private final Repository repo;
    @Autowired
    public SmsService(@Qualifier("fileRepository") Repository repo) {
        this.repo = repo;
    }
    //-------------------------------------------------


    @Override
    public void sendMessage(Message message) {
        System.out.println("Mesajiniz SMS ile gonderiliyor : " + message.getBody());
    }

    @Override
    public void saveMessage(Message message) {
        repo.save(message);
    }
}
