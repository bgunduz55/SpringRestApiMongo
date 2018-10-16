/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bgunduz.demospring01.repositories;

/**
 *
 * @author bgunduz
 */
import com.bgunduz.demospring01.models.User;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends MongoRepository<User, String>  {
    User findByid(String id);
    User findByUsername(@Param("username") String username);
    @Query("{ 'name' : ?0 }")
    List<User> findByName(@Param("name") String name);
    List<User> findFirst10Byid(@Param("id") String id);
    //List<User> findAll();
}
