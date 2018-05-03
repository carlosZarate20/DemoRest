package controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Collections;
import model.Person;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;


import service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {
	
	private List<Person> persons = new ArrayList();
	
	PersonController() {
		this.persons = buildPersons();
	}
 
	@RequestMapping(method = RequestMethod.GET)
	public List<Person> getUsers() {
		return this.persons;
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Person getUser(@PathVariable("id") Long id) {
		return this.persons.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
	}
 
	@RequestMapping(method = RequestMethod.POST)
	public Person saveUser(@RequestBody Person user) {
		Long nextId = 0L;
		if (this.persons.size() != 0) {
			Person lastUser = this.persons.stream().skip(this.persons.size() - 1).findFirst().orElse(null);
			nextId = lastUser.getId() + 1;
		}
 
		user.setId(nextId);
		this.persons.add(user);
		return user;
 
	}
 
	@RequestMapping(method = RequestMethod.PUT)
	public Person updateUser(@RequestBody Person user) {
		Person modifiedUser = this.persons.stream().filter(u -> u.getId() == user.getId()).findFirst().orElse(null);
		modifiedUser.setFirstName(user.getFirstName());
		modifiedUser.setLastName(user.getLastName());
		modifiedUser.setEmail(user.getEmail());
		return modifiedUser;
	}
 
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deleteUser(@PathVariable Long id) {
		Person deleteUser = this.persons.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
		if (deleteUser != null) {
			this.persons.remove(deleteUser);
			return true;
		} else  {
			return false;
		}
 
 
	}
	
	/*@Autowired
	PersonService ps;
	
	@RequestMapping("/all")
	public Hashtable<String, Person> getAll(){
		return ps.getAll();
	}
	
	@RequestMapping("{id}")
	public Person getPerson(@PathVariable("id") String id){
		return ps.getPerson(id);
	}*/
	
	List<Person> buildPersons() {
		List<Person> persons = new ArrayList<>();
 
		Person user1 = buildPersons(1L, "John", "Doe", "john@email.com");
		Person user2 = buildPersons(2L, "Jon", "Smith", "smith@email.com");
		Person user3 = buildPersons(3L, "Will", "Craig", "will@email.com");
		Person user4 = buildPersons(4L, "Sam", "Lernorad", "sam@email.com");
		Person user5 = buildPersons(5L, "Ross", "Doe", "ross@email.com");
 
		persons.add(user1);
		persons.add(user2);
		persons.add(user3);
		persons.add(user4);
		persons.add(user5);
 
		return persons;
 
	}
	
	Person buildPersons(Long id, String fname, String lname, String email) {
		Person user = new Person();
		user.setId(id);
		user.setFirstName(fname);
		user.setLastName(lname);
		user.setEmail(email);
		return user;
	}

}
