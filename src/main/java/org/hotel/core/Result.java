package org.hotel.core;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class Result {
    public static ResponseEntity castResponseEntityFromResult(Result result) {
        ResponseEntity responseEntity;
        if (result.isResult()) {
            responseEntity = new ResponseEntity<>("", HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(result.getErrorDescription(), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    private boolean result = true;
    private String errorDescription = "";

}
