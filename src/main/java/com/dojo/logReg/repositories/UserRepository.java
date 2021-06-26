package com.dojo.logReg.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dojo.logReg.models.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
 User findByEmail(String email);
}
