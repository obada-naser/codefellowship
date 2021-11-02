package com.example.codefellowship.Repositories;

import com.example.codefellowship.Model.UserPost;
import org.springframework.data.repository.CrudRepository;

public interface PostUserRepository extends CrudRepository<UserPost,Integer> {

    UserPost findUserPostById(int post_id);
}
