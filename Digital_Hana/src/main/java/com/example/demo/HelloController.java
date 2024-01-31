package com.example.demo;

import java.util.HashMap;

import javax.xml.crypto.Data;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.tags.Param;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;


//http://localhost:8080/getUserInfo
//기본 포트번호가 8080
//오라클을 express 버전을 설치하면 홈페이지를 만들어서 내부에서 8080을 쓰고 있기 때문에 포트 번호가 충돌날 수 있다.
//java 객체를 json형태로 바꿔준다.
@RestController
// .../hello/* 로 들어오는 요청을 처리해준다.
@RequestMapping(value="")
@CrossOrigin("*")
public class HelloController {
	
	@GetMapping("/getUserInfo")
	public HashMap<String, String> getUserInfo(){
		
		//컬렉션 클래스
		//배열 : 인덱스를 통해 읽고 쓸 수 있다.
		//Map : 키와 값의 쌍으로 구분되는 데이터를 저장하여 키값으로 데이터에 접근한다.(HashMap, Dictionary, Json ...)
		HashMap<String, String> map = new HashMap<>();
		
		map.put("name", "홍길동");
		map.put("phone", "010-3433-3222");
		map.put("address", "서울시 관악구");
		return map;
	}
	
	// 정보를 주고 받는 방식 - get 방식
	// .../getUserInfo?userid=test&username=홍길동
	
	// 새로운 방식
	// .../getUserInfo/test
	
	// post 방식
	// form 태그에 method = "POST"로 바꿔야한다.
	// add1?x=5&y=7 {x:5, y:7, result:12} -> GET
	// add2/5/7     {x:5, y:7, result:12} -> GET
	// add3         {x:5, y:7, result:12} -> POST
	
	@GetMapping("/add1")
	public HashMap<String, Object> add1(@RequestParam("x") String x, 
										@RequestParam("y") String y) {
		//HttpServletRequest 객체에 담아온다.
		//int x = Integer.parseInt(request.getParameter("x"));
		HashMap<String, Object> map = new HashMap<>();
		map.put("x", x);
		map.put("y", y);
		map.put("result", Integer.parseInt(x) + Integer.parseInt(y));
		
		return map;
	}
	
	@GetMapping("/add2/{x}/{y}")
	public HashMap<String, Object> add2(@PathVariable(name = "x") String x, 
										@PathVariable(name = "y") String y) {
		//HttpServletRequest 객체에 담아온다.
		//int x = Integer.parseInt(request.getParameter("x"));
		HashMap<String, Object> map = new HashMap<>();
		map.put("x", x);
		map.put("y", y);
		map.put("result", Integer.parseInt(x) + Integer.parseInt(y));
		
		return map;
	}
	
	@PostMapping("/add3")
	public HashMap<String, Object> add3(@RequestParam("x") int x, 
										@RequestParam("y") int y) {
		//HttpServletRequest 객체에 담아온다.
		//int x = Integer.parseInt(request.getParameter("x"));
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("x", x);
		map.put("y", y);
		map.put("result", x + y);
		
		return map;
	}
	
	//@RequestBody : JSON 받
	@PostMapping("/add4")
	public HashMap<String, Object> add4(@RequestBody HashMap<String, String> request){
		
		HashMap<String, Object> map = new HashMap<>();
		int x = Integer.parseInt(request.get("x"));
		int y = Integer.parseInt(request.get("y"));
		
		map.put("x", x);
		map.put("y", y);
		map.put("result", x + y);
		
		return map;
	}
	
//	//@RequestBody : JSON 받기
//					 데이터를 client가 json 형태로 보낼 떄
//					 json 데이터를 받아서 자바객체로 전환과정을 거친다.
//					 HashMap이나 Dto(Data transferData Object) 클래스는 DB테이블 필드와 거의 1:1,
//					 3개의 테이블을 조인해서 필요한 필드만큼 만들 수 있다.
//	//클라이언트로부터 파라미터(정보)를 받아올 떄 보통 DTO를 사용한다.
//    Restful API - 데이터를 주고 받기 위한 표준 XML이나 JSON
//	  XML - 실제 데이터를 가져오는 parsing 과정이 별도로 필요하다.(파서 프로그램도 많다.)
//	  JSON - 대세가 되어가고 있는 데이터 형식
					 
	@PostMapping("/payment")
	public HashMap<String, Object> payment(@RequestBody HashMap<String, String> request){
		
		HashMap<String, Object> map = new HashMap<>();
		int per_pay = Integer.parseInt(request.get("per_pay"));
		int work_time = Integer.parseInt(request.get("work_time"));
		
		map.put("week_pay", per_pay * work_time);
		
		return map;
	}
}
