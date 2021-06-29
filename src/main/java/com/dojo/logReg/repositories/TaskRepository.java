package com.dojo.logReg.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dojo.logReg.models.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task,Long>{
	

}
