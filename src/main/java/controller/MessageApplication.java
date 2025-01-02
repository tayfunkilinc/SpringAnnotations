package controller;

import domain.Message;
import repository.DbRepository;
import repository.FileRepository;
import repository.Repository;
import service.MailService;
import service.MessageService;
import service.WhatsappService;

public class MessageApplication {
    public static void main(String[] args) {

        Message message = new Message();

        message.setBody("Spring is COMING...");


        //bu mesaji maille gonderme yapsin
        MailService mailService = new MailService();

        mailService.sendMessage(message);

        //artik mail kullanmayalim whatshap ile gonderelim denince ne yapacagiz -- bunun icin whatsapp service olusturup yaparim
        /*WhatsappService whatsappService = new WhatsappService();
        whatsappService.sendMessage(message);*/ //--> bunun yerine referansimizi interfaceden alalim

        //ref interfaceden alalim
        /*MessageService service3 = new WhatsappService();
        service3.sendMessage(message);*/
        //herhangi bir service degisikliginde direk

        Repository repository = new FileRepository(); // DI bagimlilik enjeksiyonu -- referansi interfaceden aldikki ilerde repo degisikliklerinden etkilenmesin
        MessageService service = new WhatsappService(repository);
        service = new MailService(repository); //--> referansimiz interface oldugu icin direk mail servise gecebildim
        service.sendMessage(message);

        //gonderilen mesaji kaydedelim -- repositorye kayitlarimizi yapalim  bunuda degistirebilir DB yaparken dosyaya kayit isteyebilir onun icin interface kullaniyoruz
        service.saveMessage(message);

        MessageService service2 = new WhatsappService(repository);
        service2.sendMessage(message);
        service2.saveMessage(message);



    }
}
