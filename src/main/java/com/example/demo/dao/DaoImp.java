package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.demo.model.UserReq;

@Service
public class DaoImp implements Dao {
	public List<UserReq> list = new ArrayList<>();
	@Override
	public List<UserReq> getAll() {
		demoData();
		if (list == null)
			return null;
		return list;
	}
	@Override
	public List<UserReq> demoData() {
		list.add(new UserReq(1, "Hello", "tien nhieu de làm gì", 100000L, "BC", "Thanh DN"));
		list.add(new UserReq(2, "Hello", "nho tra đung hạn", 100000L, "BC", "idol duc"));
		list.add(new UserReq(3, "Hello", "cho em xin tien", 10000L, "DN", "Duck"));
		return list;
	}

}
