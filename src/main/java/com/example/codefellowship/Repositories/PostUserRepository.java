package com.example.codefellowship.Repositories;

import com.example.codefellowship.Model.ApplicationUser;
import com.example.codefellowship.Model.UserPost;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostUserRepository extends CrudRepository<UserPost,Integer> {
    List<UserPost> findAllByApplicationUser(ApplicationUser appUser);


}
