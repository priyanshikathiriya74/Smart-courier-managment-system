package com.smartcouriersystem.Smart.Courier.Management.System.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PackageNotFoundException extends ResponseStatusException {
    public PackageNotFoundException(long pkgId) {
        super(HttpStatus.NOT_FOUND,"Pacakge with %d id is not found".formatted(pkgId));
    }
}
