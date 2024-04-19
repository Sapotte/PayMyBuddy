package com.paymybuddy.controllers;

import com.paymybuddy.controllers.dto.PostUser;
import com.paymybuddy.services.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    UsersService usersService;

    /** Create a user's account if the account doesn't exist
     * @param user contains info to creat an account
     * @return responseEntity
     */
    @PostMapping
    @Operation(summary = "Create an account",
            description = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created successfully")
    })
    public ResponseEntity<String> addPerson(@RequestBody PostUser user) {
        var newUser = usersService.createUserAccount(user);
        if(newUser) {
            return ResponseEntity.ok("Account created successfully");
        } else {
            return ResponseEntity.badRequest().body("Account already exists");
        }
    }
}
