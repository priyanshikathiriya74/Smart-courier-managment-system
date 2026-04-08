package com.smartcouriersystem.Smart.Courier.Management.System.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LocationNotFoundException extends ResponseStatusException {
    public LocationNotFoundException(long locationId) {
        super(HttpStatus.NOT_FOUND,"There no Location Found that have %d id".formatted(locationId));
    }
}
