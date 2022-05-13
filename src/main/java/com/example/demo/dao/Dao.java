package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.UserReq;

public interface Dao {
	public List<UserReq> getAll();
	public List<UserReq> demoData();
}
