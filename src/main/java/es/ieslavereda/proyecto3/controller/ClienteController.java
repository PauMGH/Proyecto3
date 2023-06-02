package es.ieslavereda.proyecto3.controller;

import es.ieslavereda.proyecto3.service.ClienteService;
import es.ieslavereda.proyecto3.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController extends BaseController{
    @Autowired
    ClienteService service;
}
