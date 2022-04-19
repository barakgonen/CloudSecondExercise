package com.example.hw.WebServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class StartUpInitilizer {
    private final Logger logger = LoggerFactory.getLogger(StartUpInitilizer.class);
    @Autowired
    private UsersRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        List allUsers = this.repository.findAll();

        logger.info("Number of users: " + allUsers.size());

        User newUser = new User("Barak", "Gonen", "barakgonen9@gmail.com");
        logger.info("Saving new user...");
        if (!allUsers.contains(newUser)) {
            this.repository.save(newUser);
        }

        allUsers = this.repository.findAll();
        logger.info("Number of users: " + allUsers.size());
    }
}