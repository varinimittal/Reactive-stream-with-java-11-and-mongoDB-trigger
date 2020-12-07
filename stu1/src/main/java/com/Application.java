package com;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.doc.Student1;
import com.mongodb.async.client.MongoCollection;
import com.repository.StudentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication 
//public class Application implements CommandLineRunner {
@EnableReactiveMongoRepositories
public class Application {
   @Autowired 
    private StudentRepository personRepository;

	public static void main(String[] args) throws InterruptedException  {

    	ConfigurableApplicationContext application =SpringApplication.run(Application.class);
        final MongoOperations mongoOperations = application.getBean(MongoOperations.class);
        final StudentRepository concertTicketRepository = application.getBean(StudentRepository.class);
        if(mongoOperations.collectionExists("student1")) {
            mongoOperations.dropCollection("student1");
         }
        mongoOperations.createCollection("student1", CollectionOptions.empty().capped().size(9999999L).maxDocuments(100L));
        final Mono<	Student1> saveTicketOne = concertTicketRepository.save(new Student1("5","maitri", "bahuguna","ceo",20));
        saveTicketOne.subscribe();

       Thread.sleep(200);
        concertTicketRepository.findWithTailableCursorBy()
        .flatMap(ticket -> printTicketInformation(ticket))
        .subscribe();

        

    }
	

   /* public void run(String... arg0) throws Exception {
    	ArrayList<Student1> documents = new ArrayList<Student1>();

        final Student1 johnAoe = new Student1("1","varini", "mittal","cs",20);
        documents.add(johnAoe);
        final Student1 johnBoe = new Student1("2","manushi", "kapoor","manager",22);
        documents.add(johnBoe);
        final Student1 johnCoe = new Student1("3","jasleen", "kaur","head",21);
        documents.add(johnCoe);
        final Student1 johnDoe = new Student1("4","stuti", "painuly","hr",21);
        documents.add(johnDoe);
        MongoOperations mongoDatabase=mongoClient.getDatabase(MongodbManager.DBNAME);;
		MongoCollection<Student1> collection = mongoDatabase.getCollection("student1");
        

        personRepository.saveAll(Flux.just(johnAoe, johnBoe, johnCoe, johnDoe)).blockFirst();
        personRepository.save(new Student1("5","maitri", "bahuguna","ceo",20));
        personRepository.findAll().log().map(Student1::getFirstName).subscribe(System.out::println);
        personRepository.findByDept("head").log().map(Student1::getDept).subscribe(System.out::println);
        

        //personRepository.findOneByFirstName("joh").log().map(Student1::getLastName).subscribe(System.out::println);

    }*/
	private static Mono<Student1> printTicketInformation(Student1 concertTicket) {
        System.out.println(String.format("First Name: %s Last Name: %s", concertTicket.getFirstName(), concertTicket.getLastName()));
        return Mono.just(concertTicket);
    }
	
}
