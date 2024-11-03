package controller;

package com.example.unifor.controller;

import com.example.entity.Reserva;
import com.example.entity.User;
import com.example.service.ReservaService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private UsuarioService usuarioService;

    //colocar esse metodo?
    @GetMapping
    public List<Reserva> getAllReservas() {
        return reservaService.findAllReservas();
    }

    @PostMapping
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) {
        Reserva novaReserva = reservaService.saveReserva(reserva);
        return new ResponseEntity<>(novaReserva, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/{reservaId}/user/{userId}")
    public ResponseEntity<User> addReservaToUser(@PathVariable Long reservaId, @PathVariable Long userId) {
        try {
            User userAtualizado = userService.addReservaToUser(userioId, reservaId);
            return new ResponseEntity<>(userAtualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

