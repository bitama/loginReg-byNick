package com.dojo.logReg.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dojo.logReg.models.Menu;

@Repository
public interface MenuRepository extends CrudRepository<Menu,Long>{
	

}
