package com.example.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.model.Dept;
import com.example.model.Emp;
import com.example.repository.DeptRepository;
import com.example.repository.EmpRepository;

@Service
@Transactional
public class CompanyService {

	@Autowired
	private DeptRepository deptRepository;

	@Autowired
	private EmpRepository empRepository;

	public Optional<Emp> selectEmp(Long empno) {
		return empRepository.findById(empno);
	}

	public Optional<Dept> selectDeptno(Long deptno) {
		return deptRepository.findById(deptno);
	}

	public List<Emp> empContains(Emp emp) {
		return empRepository.findAll((root, query, builder) -> {
			Predicate predicate = builder.conjunction();
			if (StringUtils.hasText(emp.getJob())) {
				predicate = builder.and(predicate, builder.equal(root.get("job"), emp.getJob()));
			}
			if (!Objects.isNull(emp.getDeptno())) {
				predicate = builder.and(predicate, builder.equal(root.get("deptno"), emp.getDeptno()));
			}
			if (!Objects.isNull(emp.getEmpno())) {
				predicate = builder.and(predicate, builder.equal(root.get("empno"), emp.getEmpno()));
			}
			if(StringUtils.hasText(emp.getEname())) {
				predicate = builder.and(predicate, builder.like(root.get("ename"), "%" + emp.getEname() + "%"));
			}
			return predicate;
		});
	}

	@Autowired
	private EntityManager entityManager;

	public List<Emp> nameContains(Emp emp) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Emp> query = builder.createQuery(Emp.class);
		Root<Emp> root = query.from(Emp.class);

		Predicate predicate = builder.conjunction();
		if (StringUtils.hasText(emp.getJob())) {
			predicate = builder.and(predicate, builder.equal(root.get("job"), emp.getJob()));
		}
		if (!Objects.isNull(emp.getDeptno())) {
			predicate = builder.and(predicate, builder.equal(root.get("deptno"), emp.getDeptno()));
		}
		if (!Objects.isNull(emp.getEmpno())) {
			predicate = builder.and(predicate, builder.equal(root.get("empno"), emp.getEmpno()));
		}
		if(StringUtils.hasText(emp.getEname())) {
			predicate = builder.and(predicate, builder.like(root.get("ename"), "%" + emp.getEname() + "%"));
		}
		query.select(root).where(predicate);

		TypedQuery<Emp> typedQuery = entityManager.createQuery(query);
		return typedQuery.getResultList();
	}

}