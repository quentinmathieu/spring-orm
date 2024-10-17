package fr.afpa.orm;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.time.LocalDateTime;
import java.time.LocalTime;



import fr.afpa.orm.entities.Account;
import fr.afpa.orm.entities.Client;
import fr.afpa.orm.repositories.AccountRepository;
import fr.afpa.orm.repositories.ClientRepository;

public class FixtureGenerator {
    private AccountRepository accountRepository;
    private ClientRepository clientRepository;

    public FixtureGenerator(AccountRepository accountRepository, ClientRepository clientRepository){
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
        this.generate(5);
    }
    
    private void generate(int nb){
        String[] names = {"Aaron", "Caitlin", "Mazda", "Bianca", "Brunehilde", "Betsie", "Fabricio", "accessories", "accompaniments", "accouterments", "appliances", "appurtenances", "articles", "attachments", "baggage", "belongings", "contraptions", "contrivances", "devices", "equipage", "facilities", "fittings", "gadgets", "habiliments", "impedimenta", "materiel", "outfit", "paraphernalia", "provisions", "rig", "setup", "shebang", "stock", "store", "stuff", "tackle", "things", "tools", "trappings", "traps", "utensils"};
        for (Integer i=0; i<nb; i++){
            Client newClient = clientRepository.save(new Client(names[randInt(names.length-1)], names[randInt(names.length-1)], names[randInt(names.length-1)]+"@gmail.com", this.generateDate()));
            accountRepository.save(new Account(BigDecimal.valueOf(randInt(10000)), generateDateTime(), newClient));
        }

        System.out.println("::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println(":::::::::::::  DATAS GENERATED :::::::::::::");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::");
    }

    private LocalDate generateDate(){
        return LocalDate.of(randInt(1999), 12, 28);
    }

    private LocalDateTime generateDateTime(){
        return LocalDateTime.of(generateDate(), LocalTime.now());
    }

    public static Integer randInt(Integer max){
        return randInt(0, max);
    }

    public static Integer randInt(Integer min, Integer max) {

        return ThreadLocalRandom.current().nextInt(min, max + 1 );
    }
}
