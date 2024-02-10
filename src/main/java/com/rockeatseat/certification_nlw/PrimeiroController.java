package com.rockeatseat.certification_nlw;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gusta
 */
@RestController
@RequestMapping("/primeiraController")
public class PrimeiroController {
    
    @GetMapping
    public Usuario retornoPrimeiraController() {
        Usuario usuario = new Usuario("Gustavo", 23);
        return usuario;
    }
    
    record Usuario(String nome, int idade) {}
}
