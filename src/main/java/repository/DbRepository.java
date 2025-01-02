package repository;

import domain.Message;

public class DbRepository implements Repository{
    @Override
    public void save(Message message) {
        System.out.println("Mesajınız veritabanına kaydediliyor.");
    }
}
