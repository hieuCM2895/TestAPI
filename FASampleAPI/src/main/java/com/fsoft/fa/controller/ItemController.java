package com.fsoft.fa.controller;

import com.fsoft.fa.model.Human;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


/**
 * 
 * @author haimh
 *
 */
@RestController // annotation
@RequestMapping(path = "/item", produces = APPLICATION_JSON_VALUE)
//localhost:8080/item/get-list-items
public class ItemController {
	
	/**
	 * 
	 * @return
	 */
    @GetMapping(value = "/get-list-items")
    public List<Human> getHumans() {
        try {
        	Integer.parseInt("a");
        	Human human = new Human();
        	human.setName("Hai");
        	Human human2 = new Human();
        	human2.setName("Hai-2");
        	List<Human> humans = new ArrayList<Human>();
        	humans.add(human);
        	humans.add(human2);
        	
        	return humans;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PostMapping(value = "/create-user")
    public List<Human> createUser(@RequestBody Human human) {
        try {
        	System.out.println("Insert into DB user: " + human.getName());
        	return Arrays.asList(human);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @DeleteMapping(value = "/delete-user/{id}")
    public String deleteUser(@PathVariable(name = "id") String id) {
        try {
            throw new Exception("Database exception");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
