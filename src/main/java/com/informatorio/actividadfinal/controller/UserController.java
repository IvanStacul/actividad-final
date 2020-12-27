package com.informatorio.actividadfinal.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.informatorio.actividadfinal.model.User;
import com.informatorio.actividadfinal.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // Crear un usuario
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody User user){
        return new ResponseEntity<>(userRepository.save(user),HttpStatus.CREATED);
    }
    
    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Modificar un usuario
    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(@PathVariable Integer id, @Valid @RequestBody User user) {
        User userEdit = userRepository.findById(id).get();
        userEdit.setFirstName(user.getFirstName());
        userEdit.setLastName(user.getLastName());
        userEdit.setCity(user.getCity());
        userEdit.setCountry(user.getCountry());
        userEdit.setProvince(user.getProvince());
        userEdit.setEmail(user.getEmail());
        return new ResponseEntity<>(userRepository.save(userEdit), HttpStatus.OK);
    }

    // Listar todos los usuarios
    @GetMapping("/all")
    public ResponseEntity<List<User>> list(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    // Devolver usuario mediante id
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id) {
        return new ResponseEntity<>(userRepository.findById(id).get(),HttpStatus.OK);
    }

    //Devolver usuarios por ciudad
    @GetMapping("city/{city}")
    public ResponseEntity<List<User>> getUserByCity(@PathVariable String city){
        return new ResponseEntity<>(userRepository.findByCity(city),HttpStatus.OK);
    }

    //Devolver usuarios creados despues de una fecha
    @GetMapping("afterDate/{date}")
    public ResponseEntity<List<User>> getUserAfterDate(@PathVariable(value="date") @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate date){
        return new ResponseEntity<>(userRepository.findAllByCreationDateIsAfter(date), HttpStatus.OK);
    }
}
