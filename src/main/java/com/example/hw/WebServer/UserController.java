package com.example.hw.WebServer;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("users")
    public ResponseEntity<String> getAll() {
        List<User> userList = usersRepository.findAll();
        return new ResponseEntity<>(new Gson().toJson(userList), HttpStatus.OK);
    }

    @GetMapping("users/{firstName}/{lastName}")
    public ResponseEntity<String> getByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
        List<User> userList = usersRepository.findAll();

        Optional<User> user = userList.stream().filter(user1 -> user1.getFirstName().equals(firstName) && user1.getLastName().equals(lastName)).findFirst();
        if (user.isPresent()) {
            return new ResponseEntity<>(new Gson().toJson(user.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}