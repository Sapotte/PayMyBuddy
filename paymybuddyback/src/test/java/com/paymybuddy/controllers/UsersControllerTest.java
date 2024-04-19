package com.paymybuddy.controllers;

import com.paymybuddy.controllers.dto.PostUser;
import com.paymybuddy.services.UsersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsersControllerTest {
    @InjectMocks
    UsersController usersController;
    @Mock
    UsersService usersService;

    @Test
    void createUserOk() {
        PostUser user = new PostUser();
        when(usersService.createUserAccount(any())).thenReturn(true);

        var response = usersController.addPerson(user);

        verify(usersService, times(1)).createUserAccount(user);
        assertEquals(response.getStatusCode(), HttpStatusCode.valueOf(200));
    }

    @Test
    void createUserAlreadyExistsKo() {
        PostUser user = new PostUser();
        when(usersService.createUserAccount(any())).thenReturn(false);

        var response = usersController.addPerson(user);

        verify(usersService, times(1)).createUserAccount(user);
        assertEquals(response.getStatusCode(), HttpStatusCode.valueOf(400));
    }
}
