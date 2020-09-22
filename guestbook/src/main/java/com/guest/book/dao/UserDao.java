package com.guest.book.dao;

import java.util.List;

import com.guest.book.entity.USER;
import com.guest.book.model.UserModel;

public interface UserDao {

	public  List<USER>  validateUser(UserModel model);	
	
}
