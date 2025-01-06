package com.tpe.service;

import com.tpe.domain.Message;
import com.tpe.repository.DbRepository;
import com.tpe.repository.Repository;

public class MailService implements MessageService {

    //private DbRepository repo=new DbRepository();//mailservice dbrepositorye sıkı sıkı bağımlı olur

    private Repository repo; // bunu new DbRepository() burada direk vermiyorum parametreli const ile ilerde alacagimi belirtip dinamik hale getiriyorum

    public MailService(Repository repo) {
        this.repo = repo;
    }

    public MailService() {
    }

    public void sendMessage(Message message){
        System.out.println("Mesajiniz mail ile gonderiliyor...: " + message.getBody());
    }

    @Override
    public void saveMessage(Message message) {

        //DbRepository repo = new DbRepository(); burda olusturmak surekli obje olusturur method kullandikca sorunlu bur yer
        repo.save(message);

    }
}
