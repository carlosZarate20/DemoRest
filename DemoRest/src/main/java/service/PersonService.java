package service;

import java.util.Hashtable;
import org.springframework.stereotype.Service;
import model.User;

@Service
public class PersonService {
	Hashtable<String, User> persons = new Hashtable<String, User>();
	public PersonService(){
		/*Person p = new Person();
		p.setId("1");
		p.setNombre("Carlos");
		p.setApellido("Zarate");
		p.setEdad(22);
		persons.put("1",p);
		
		p = new Person();
		p.setId("2");
		p.setNombre("Marita");
		p.setApellido("Lozano");
		p.setEdad(21);
		persons.put("2",p);*/
	}
	
	public User getPerson(String id){
		if(persons.containsKey(id)){
			return persons.get(id);
		}else{
			return null;
		}
	}
	
	public Hashtable<String,User> getAll(){
		return persons;
	}
	

}
