package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.demo.model.UserReq;

@Service
public class DaoImp implements Dao {
	public List<UserReq> users = new ArrayList<>();
	@Override
	public List<UserReq> getAll() {
		addData();
		if (users == null)
			return null;
		return users;
	}
	@Override
	public List<UserReq> addData() {
		users.add(new UserReq(1, "Hello", "tien nhieu de làm gì", 100000L, "BC", "Thanh DN"));
		users.add(new UserReq(3, "Hello", "cho em xin tien", 10000L, "DN", "Duck"));
		return users;
	}

}
