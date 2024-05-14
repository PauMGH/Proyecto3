package es.ieslavereda.proyecto3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RequestMapping("/api/v")
public abstract class BaseController {

    protected final Logger LOGGER = Logger.getLogger(getClass().getCanonicalName());

    protected ResponseEntity<?> getResponseError(SQLException e) {

        Map<String,String> map = new HashMap<>();

        map.put("errorCode",String.valueOf(e.getErrorCode()));
        map.put("message",e.getMessage());

        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
