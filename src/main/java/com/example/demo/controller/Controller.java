package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.SendMail.sendMessage;
import com.example.demo.dao.Dao;
import com.example.demo.dao.DaoImp;
import com.example.demo.model.UserReq;
import com.example.demo.response.addResponse;
import com.example.demo.response.deleteResponse;
import com.example.demo.response.updateResponse;

@RestController
public class Controller {
	Logger log = LoggerFactory.getLogger(Controller.class);
	List<UserReq> users = new ArrayList<>();
	
//	@Value("${notification.channel}")
//	private String nof;
	
	@Autowired
	sendMessage s;
	
	@Autowired
	Dao d = new DaoImp() ;
	
	@GetMapping("/get")
	public ResponseEntity<List<UserReq>> getAllUser() {
		d.addData();
		if (users.size() == 0) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		log.info("add Data Success !");
		return new ResponseEntity<>(users, HttpStatus.OK);

	}
	@GetMapping("/gets")
	public List<UserReq> getAll(){
		//d.demoData();
		if(users.size() == 0) return null;
		//if(s == null) return null;
		return users;
		
	}
	@GetMapping("/send")
	public ResponseEntity<String> sendMai() throws UnsupportedEncodingException, MessagingException{
		getAll();
//		if(nof == "EMAIL") {
			s.sendMail("thang.hoangngoc.it@gmail.com");
			log.info("Send Message Success !");
			return new ResponseEntity<>("Send Ok", HttpStatus.OK);
//		}
		//else if(nof == "EMAIL") {
//			return new ResponseEntity<>("Send Ok", HttpStatus.OK);
		//}
		
	}

	@PostMapping("/post")
	public ResponseEntity<addResponse> UserAdd(@RequestBody UserReq m) {
		if ( m == null || m.getUserId() <= 0 || m.getCurrent_balance() <= 0) {
			addResponse addF = new addResponse("1", "Add failed", "1", LocalDateTime.now().toString(),String.valueOf(m.getUserId()));
			log.error(String.valueOf(addF));
			return new ResponseEntity<>(addF, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			addResponse addT = new addResponse("0", "Added !", "0", LocalDateTime.now().toString(),String.valueOf(m.getUserId()));
			users.add(m);
			log.info(String.valueOf(addT));
			
			return new ResponseEntity<>(addT, HttpStatus.CREATED);		
		}
	}

	@PutMapping("/put")
	public ResponseEntity<updateResponse> updateUser(@RequestBody UserReq m) {
		if (m == null || users.size() == 0) {
			updateResponse updF = new updateResponse("2", "Update failed", "2", LocalDateTime.now().toString(),
					String.valueOf(m.getUserId()));
			log.error(String.valueOf(updF));
			return new ResponseEntity<>(updF, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		updateResponse updT = new updateResponse();
		users.forEach(t -> {
			if (t.getUserId() != m.getUserId())
				return;
			t.setTitle(m.getTitle());
			t.setContent(m.getContent());
			t.setCurrent_balance(m.getCurrent_balance());
			t.setType(m.getType());
			t.setPartner(m.getPartner());
			updT.setResponseId("0");
			updT.setResponseMessage("Updated !");
			updT.setResponseCode("0");
			updT.setResponseTime(LocalDateTime.now().toString());
			updT.setUpdateId(String.valueOf(m.getUserId()));
		});
		log.info(String.valueOf(updT));
		return new ResponseEntity<>(updT, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<deleteResponse> deleteUser(@RequestParam(name = "id") int id) {
		deleteResponse delF = new deleteResponse();
		if (id <= 0 || users.size() == 0) {
			delF.setResponseId("3");
			delF.setResponseMessage("Delete failed !");
			delF.setResponseCode("3");
			delF.setResponseTime(LocalDateTime.now().toString());
			delF.setDeleteId(String.valueOf(id));
			log.error(String.valueOf(delF));
			return new ResponseEntity<>(delF, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		deleteResponse delT = new deleteResponse();	
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUserId() == id) {
				users.remove(i);
				delT.setResponseId("0");
				delT.setResponseMessage("Deleted !");
				delT.setResponseCode("0");
				delT.setResponseTime(LocalDateTime.now().toString());
				delT.setDeleteId(String.valueOf(id));
			}else {
				delF.setResponseId("3");
				delF.setResponseMessage("Delete failed !");
				delF.setResponseCode("3");
				delF.setResponseTime(LocalDateTime.now().toString());
				delF.setDeleteId(String.valueOf(id));
				log.error(String.valueOf(delF));
				return new ResponseEntity<>(delF, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		log.info(String.valueOf(delT));
		
		return new ResponseEntity<>(delT, HttpStatus.ACCEPTED);
	}
}
