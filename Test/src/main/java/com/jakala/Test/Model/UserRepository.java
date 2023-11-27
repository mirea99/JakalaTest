package com.jakala.Test.Model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends CrudRepository<User,Integer>
{

	List<User> findByNameContaining(String name);
	List<User> findByUsertypeContaining(String userType);
	
}
