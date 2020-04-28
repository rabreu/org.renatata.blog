package org.renatata.blog.controller;

import org.json.JSONException;
import org.renatata.blog.entity.User;
import org.renatata.blog.model.UserResponse;
import org.renatata.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    @ResponseBody
    public ResponseEntity<UserResponse> add(@RequestBody User user) throws JSONException {
        try {
            Optional<User> userExists = userService.findUserByEmail(user.getEmail());
            if (userExists.isPresent()) {
                return new ResponseEntity<UserResponse>(new UserResponse(false, "User already exists.", userExists.get().getEmail()), HttpStatus.BAD_REQUEST);
            }

            userService.add(user);
            return new ResponseEntity<>(new UserResponse(true, "User created.", user.getEmail()), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new UserResponse(false, e.toString(), user.getEmail()), HttpStatus.BAD_REQUEST);
        }
    }
}
