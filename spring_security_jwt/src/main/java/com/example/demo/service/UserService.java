package com.example.demo.service;

import java.util.List;

import com.example.demo.model.UserDTO;

public interface UserService {
	public List<UserDTO> findAll();
	
	public void deleteUser(String username);
	
	public void saveUser(UserDTO userDTO);
}
