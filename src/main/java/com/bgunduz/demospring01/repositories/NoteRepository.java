/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bgunduz.demospring01.repositories;

import com.bgunduz.demospring01.models.Note;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bgunduz
 */
@Repository
public interface NoteRepository extends MongoRepository<Note, String>  {
    List<Note> findByusername(String username);

    public Note findByid(String id);
}
