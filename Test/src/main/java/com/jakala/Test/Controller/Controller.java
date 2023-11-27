package com.jakala.Test.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jakala.Test.Model.Contract;
import com.jakala.Test.Model.ContractRepository;
import com.jakala.Test.Model.User;
import com.jakala.Test.Model.UserRepository;

@RestController
public class Controller 
{

	@Autowired
	public UserRepository repo;
	public ContractRepository contractrepo;

	@RequestMapping("/Test/all")
	public Iterable<User> findAll()
	{
		return repo.findAll();
	}
	
	@GetMapping("/Test/users/{id}")
	public ResponseEntity<User> findById(@PathVariable("id") int id)
	{
		
		
		Optional <User> res = repo.findById(id);
		
		return 	res.isPresent() 										?
				new ResponseEntity<User>(res.get(), HttpStatus.OK)	:
				new ResponseEntity<User>(HttpStatus.NOT_FOUND)		;
		
	}
	
	
	@DeleteMapping("/Test/users/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id)
    {
        if(repo.findById(id).isEmpty())
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND); // 404

        repo.deleteById(id);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }
	
	@PutMapping("/Test/users/{id}")
	public ResponseEntity<User> updateUser
	(@PathVariable("id")int id, @RequestBody User u)
	{
		System.out.print(u);
		if(repo.findById(id).isEmpty())
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND); 	//errore 404
		if (id!=u.getId())
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST); 	//errore 400
		return new ResponseEntity<User>(repo.save(u), HttpStatus.OK);
	}
	
	@PostMapping("/Test/users")
	public ResponseEntity<List<User>> addUsers(@RequestBody List<User> users)
	{
		for(User u:users)
			if(!u.isValid())
				return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
		
		repo.saveAll(users);
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	
	@GetMapping("/Test/users/byname/{key}")
	public List<User> findByname(@PathVariable("key") String key)
	{
		return repo.findByNameContaining(key);
	}
	@GetMapping("/Test/users/byusertype/{key}")
	public List<User> findByusertype(@PathVariable("key") String key)
	{
		return repo.findByUsertypeContaining(key);
	}
	
	@GetMapping("/Test/contracts/bycontracttype/{key}")
	public List<Contract> findByContractype(@PathVariable("key") String key)
	{
		return contractrepo.findByContractType(key);
	}
	
	@GetMapping("/user/{userType}")//api che recupera i contratti di un utente
    public List<Contract> getContractsByUserType(@PathVariable String userType) 
	{
        return contractrepo.findContractsByUserType(userType);
    }
	@PostMapping("/Test/users/{id}/addcontract")
	public ResponseEntity<User> addContract(@PathVariable("id") int id, @RequestBody Contract c)
	{
		Optional<User> u = repo.findById(id);
		
		if(u.isEmpty())
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		
		u.get().addContract(c);
		repo.save(u.get());
		return new ResponseEntity<User>(u.get(), HttpStatus.OK);
	}
	
	
	
}
