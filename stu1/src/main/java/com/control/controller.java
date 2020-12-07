package com.control;



import java.util.List;
import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.time.Duration;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doc.Student1;

import com.mongodb.CursorType;
import com.repository.StudentRepository;


import io.swagger.annotations.ApiOperation;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
//@RequestMapping("/rest/studentsss/all")
public class controller {
	

	private StudentRepository employeeRepository;

	public controller(StudentRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
	@ApiOperation(value = "Search student with id", response = Mono.class)
	@GetMapping("/student/id")
	public Mono<Student1> getById(@RequestParam(value="id") final String id) {
	
	return employeeRepository.findById(id);
	}
	@ApiOperation(value = "View a list of students", response = Flux.class)
	@GetMapping("/student/all")
	 public Flux<Student1> getAll() {
		
        return employeeRepository
                .findAll();
    }
	@ApiOperation(value = "Search student by department ", response = Flux.class)
	@GetMapping("/student/{dept}")
	public Flux<Student1> getByDepartment(@PathVariable("dept") final String department) {
		
		return employeeRepository.findByDept(department);
	}
	@ApiOperation(value = "View a list of students", response = Mono.class)
	@PostMapping("/student/tail")
    public Mono<Student1> post(@RequestBody Student1 example){
       return employeeRepository.save(example);
    }
	//@ApiOperation(value = "View a list of students", response = Flux.class)
	@GetMapping(value="/student/tail",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	 public Flux<Student1> All() {
		
        return this.employeeRepository.findWithTailableCursorBy().delayElements(Duration.ofSeconds(1));
    }
	
    /*Mono<Student1> newEmployee(@RequestBody Student1 newEmployee) {
      return employeeRepository.save(newEmployee);
    }*/
	@PostMapping("/student/saveMany")
	Flux<Student1> saveMany(@RequestBody() final List<Student1> student){
		return employeeRepository.saveAll(student);
	}
	

}
