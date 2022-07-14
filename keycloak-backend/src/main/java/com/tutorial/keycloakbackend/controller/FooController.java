package com.tutorial.keycloakbackend.controller;

import com.tutorial.keycloakbackend.dto.ResponseMessage;
import com.tutorial.keycloakbackend.model.Foo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author reynaldo
 * @Date 9/07/2022
 */
@RestController
@RequestMapping("/foo")
@CrossOrigin(origins = "*")
public class FooController {

    List<Foo> fooList =
            Stream.of(new Foo(1,"foo 1"),
                    new Foo(2,"foo 2"),
                    new Foo(3,"foo 3")
            ).collect(Collectors.toList());

    //@RolesAllowed("backend-admin")
    @GetMapping("/list")
    public ResponseEntity<List<Foo>> list(){
        return new ResponseEntity(fooList, HttpStatus.OK);
    }

    //@RolesAllowed("backend-user")
    @GetMapping("/detail/{id}")
    public ResponseEntity<Foo> detail(@PathVariable("id") int id){
        Foo foo = fooList.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
        return new ResponseEntity(foo, HttpStatus.OK);
    }

    //@RolesAllowed("backend-admin")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Foo foo){
        int maxIndex = fooList.stream().max(Comparator.comparing(m -> m.getId())).get().getId();
        foo.setId(maxIndex + 1);
        fooList.add(foo);
        return new ResponseEntity(new ResponseMessage("creado"), HttpStatus.CREATED);
    }

    //@RolesAllowed("backend-admin")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Foo foo){
        Foo fooUpdate = fooList.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
        fooUpdate.setName(foo.getName());
        return new ResponseEntity(new ResponseMessage("actualizado"), HttpStatus.OK);
    }

    //@RolesAllowed("backend-admin")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        Foo foo = fooList.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
        fooList.remove(foo);
        return new ResponseEntity(new ResponseMessage("eliminado"), HttpStatus.OK);
    }
}
