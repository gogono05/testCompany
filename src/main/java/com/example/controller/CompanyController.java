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
import com.example.service.CompanyService;

@Controller
@RequestMapping("/api")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	
	@GetMapping("/table")
	public String table(Model model,@ModelAttribute("emp") Emp emp) {
		return "table.html";
	}
	@PostMapping("/selectemp")
	public String emps(Model model ,@ModelAttribute("emp") Emp emp) {
		System.out.println(emp);
		List<Emp> emplist= companyService.nameContains(emp);;
		model.addAttribute("emplist",emplist);
		return "table.html";
	}
	
}
