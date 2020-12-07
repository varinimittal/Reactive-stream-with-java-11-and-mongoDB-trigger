package com.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;

import com.doc.Student1;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepository extends ReactiveMongoRepository < Student1, String > {
	
    Flux<Student1> findByFirstName(final String firstName);

    Mono < Student1 > findOneByFirstName(final String firstName);

	Flux<Student1> findByDept(final String dept);

	Mono<Student1> findById(final String id);
	@Tailable
	  Flux<Student1> findWithTailableCursorBy();

}