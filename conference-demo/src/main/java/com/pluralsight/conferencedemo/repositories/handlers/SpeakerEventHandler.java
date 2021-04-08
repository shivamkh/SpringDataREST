package com.pluralsight.conferencedemo.repositories.handlers;


import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.util.HashSet;

@Component
@RepositoryEventHandler
public class SpeakerEventHandler {
    @Autowired
    private SpeakerJpaRepository repository;

    @HandleBeforeCreate
    public void handleSpeakerCreate(Speaker s){
        //want to enforce unique speaker first name
        Speaker foundSpeaker = repository.findFirstByFirstName(s.getFirstName());
        if(foundSpeaker != null){
            System.out.println("First Name need to be unique");
            throw new ConstraintViolationException("Fisrt name need to be unique", new HashSet<>());
        }
    }
}
