/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bgunduz.demospring01.controllers;

import com.bgunduz.demospring01.models.AuthUser;
import com.bgunduz.demospring01.models.Note;
import com.bgunduz.demospring01.repositories.AuthUserRepository;
import com.bgunduz.demospring01.repositories.NoteRepository;
import com.bgunduz.demospring01.services.EmailSendService;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bgunduz
 */
@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = "*")
public class NoteController {
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    private EmailSendService mailSender;
    @Autowired
    private AuthUserRepository authUserRepository;
    
    @GetMapping("/noteByUsername")
    public List<Note> getNotesByUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Note> notes = noteRepository.findByusername(auth.getName());
        return notes;
    }
    
    @PostMapping("/addNote")
    public Boolean addNote(@RequestParam("note") String note) throws MessagingException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AuthUser user = authUserRepository.findByUsername(auth.getName());
        Note not = new Note();
        not.setNote(note);
        not.setUsername(auth.getName());
        noteRepository.insert(not);
        mailSender.sendActivationMail(user.getEmail(), "Created Note", "Dear " +user.getName()+", You have created a note : \""+note+ "\" ");
        return true;
    }
    @PostMapping("/updateNote")
    public Boolean updateNote(@RequestParam("id") String id, @RequestParam("note") String note) throws MessagingException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AuthUser user = authUserRepository.findByUsername(auth.getName());
        Note not = new Note();
        not = noteRepository.findByid(id);
        not.setId(id);
        not.setNote(note);
        noteRepository.save(not);
        mailSender.sendActivationMail(user.getEmail(), "Updated Note", "Dear " +user.getName()+", You have updated a note : \""+note+ "\" ");
        return true;
    }
}
