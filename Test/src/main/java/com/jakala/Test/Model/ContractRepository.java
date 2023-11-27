package com.jakala.Test.Model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ContractRepository extends CrudRepository<Contract,Integer>
{
		List<Contract> findByStartDate(@Param("createdOn") LocalDate createdOn);
	
	
		List<Contract> findByContractType(@Param("contractType") String contracttype );
           
	
		List<Contract> findContractsByUserType(@Param("userType") String userType);
}
