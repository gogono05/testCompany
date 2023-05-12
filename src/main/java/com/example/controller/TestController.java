package com.example.controller;

import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Dept;
import com.example.model.Emp;
import com.example.service.CompanyService;

@RestController("/test")
public class TestController {

	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/getEmpno")
	@ResponseBody
	public Emp getByEname(Model model, @RequestParam String id) {
		Emp emp = null;
		String str = "^[0-9]+$";
		Pattern pattern = Pattern.compile(str);
		if (pattern.matcher(id).matches()) {
			Optional<Emp> optionalEmp = companyService.selectEmp(Long.parseLong(id));
			model.addAttribute("emp", optionalEmp.get());
			return optionalEmp.get();
		}
		return emp;
	}
	@GetMapping("/getDeptno")
	public Dept getByDeptno(Model model, @RequestParam Long id) {
		Dept dept = null;
		String str = "^[0-9]+$";
//		Pattern pattern = Pattern.compile(str);
//		if (pattern.matcher(id).matches()) {
			Optional<Dept> optionalEmp = companyService.selectDeptno(id);
//			model.addAttribute("Deptno", optionalEmp.get());
			return optionalEmp.get();
		
//		return dept;
	}
}
