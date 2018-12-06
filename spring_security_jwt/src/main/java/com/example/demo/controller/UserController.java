package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserDTO;
import com.example.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> findAllTest(){
		return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
	}
	@DeleteMapping("/users/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable String username){
		userService.deleteUser(username);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	@PostMapping("/users")
	public ResponseEntity<Void> UpdateUser(@RequestBody UserDTO userDTO){
		userService.saveUser(userDTO);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
