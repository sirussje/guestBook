package com.guest.book.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.guest.book.controller.GuestBookController;
import com.guest.book.dao.UserDao;
import com.guest.book.entity.USER;
import com.guest.book.model.UserModel;

public class UserServiceImpl implements UserService {
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserDao userdao;
	@Override
	public UserModel validateUser(UserModel model) {
		logger.info("index");
		List<USER> status=	userdao.validateUser(model);

	if (status.isEmpty()) {
			model.setStatus("failure");
		}
	else {
		model.setStatus("success");
	}
	logger.info("login Serv End");
	return model;
	}

}
