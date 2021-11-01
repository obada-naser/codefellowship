package com.example.codefellowship.Repositories;

import com.example.codefellowship.Model.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<ApplicationUser,Integer> {
    ApplicationUser findByUserName(String userName);


}
