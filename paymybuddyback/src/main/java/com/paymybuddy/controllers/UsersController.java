package com.paymybuddy.controllers;

import com.paymybuddy.controllers.dto.PostUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {
    @PostMapping
    @Operation(summary = "Create an account",
            description = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created successfully")
    })
    public ResponseEntity<String> addPerson(@RequestBody PostUser user) {
        throw new NotImplementedException();
    }
}
