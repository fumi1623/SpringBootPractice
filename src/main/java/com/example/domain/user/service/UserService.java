package com.example.domain.user.service;

import java.util.List;

import com.example.domain.user.model.MUser;


public interface UserService {
	
	public void signup(MUser user);
	
	public List<MUser> getUsers();
}
