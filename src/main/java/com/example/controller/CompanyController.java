package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Emp;
import com.example.model.QueryEmp;
import com.example.service.CompanyService;

@Controller
@RequestMapping("/api")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/table")
	public String table(Model model,@ModelAttribute("queryEmp") QueryEmp queryEmp) {
		return "table.html";
	}
	@PostMapping("/selectemp")
	public String emps(Model model ,@ModelAttribute("queryEmp") QueryEmp queryEmp) {
		System.out.println(queryEmp);
		List<Emp> emplist= companyService.empContains(queryEmp);
		model.addAttribute("emplist",emplist);
		return "table.html";
	}
	
}
 