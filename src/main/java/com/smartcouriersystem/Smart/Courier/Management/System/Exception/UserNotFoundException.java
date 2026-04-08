package com.smartcouriersystem.Smart.Courier.Management.System.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {
    public UserNotFoundException(long userId) {
        super(HttpStatus.NOT_FOUND,"There is no User with %d id".formatted(userId));
    }
}
