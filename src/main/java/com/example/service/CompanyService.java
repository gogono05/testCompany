package com.example.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.example.model.QueryEmp;
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

	public List<Emp> empContains(QueryEmp queryEmp) {
		return empRepository.findAll((root, query, builder) -> {
			BigDecimal salLowLimit = queryEmp.getSalLowLimit();
			BigDecimal salUpLimit = queryEmp.getSalUpLimit();
			String empStartDate = queryEmp.getStartDate();
			String empEndDate = queryEmp.getEndDate();
			Predicate predicate = builder.conjunction();
			if (StringUtils.hasText(queryEmp.getJob())) {
				predicate = builder.and(predicate, builder.equal(root.get("job"), queryEmp.getJob()));
			}
			if (StringUtils.hasText(queryEmp.getDname())) {
				Optional<Dept> optionalDept = deptRepository.findByDname(queryEmp.getDname());
				if (optionalDept.isPresent()) {
					Dept dept = optionalDept.get();
					predicate = builder.and(predicate, builder.equal(root.get("deptno"), dept.getDeptno()));
				}
			}
			if (StringUtils.hasText(queryEmp.getEname())) {
//				String ename = queryEmp.getEname().replaceAll("%", ".");
//				queryEmp.setEname(ename.replaceAll("_", "."));
				predicate = builder.and(predicate, builder.like(root.get("ename"), "%" + queryEmp.getEname() + "%"));
			}
			if (StringUtils.hasText(empStartDate) || StringUtils.hasText(empEndDate)) {
				LocalDate startDate = formatDate(empStartDate);
				LocalDate endDate = formatDate(empEndDate);
				if (startDate != null && endDate != null) {
					predicate = builder.and(predicate,
							builder.between(root.get("hiredate"), startDate, endDate.plusDays(1)));
				} else if (startDate != null) {
					predicate = builder.and(predicate, builder.greaterThanOrEqualTo(root.get("hiredate"), startDate));
				} else if (endDate != null) {
					predicate = builder.and(predicate, builder.lessThan(root.get("hiredate"), endDate.plusDays(1)));
				}
			}
			if (salUpLimit != null && salLowLimit != null) {
				predicate = builder.and(predicate, builder.between(root.get("sal"), salLowLimit, salUpLimit));
			} else if (queryEmp.getSalLowLimit() != null) {
				predicate = builder.and(predicate, builder.greaterThanOrEqualTo(root.get("sal"), salLowLimit));
			} else if (queryEmp.getSalUpLimit() != null) {
				predicate = builder.and(predicate, builder.lessThanOrEqualTo(root.get("sal"), salUpLimit));
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
		if (StringUtils.hasText(emp.getEname())) {
			predicate = builder.and(predicate, builder.like(root.get("ename"), "%" + emp.getEname() + "%"));
		}
		query.select(root).where(predicate);

		TypedQuery<Emp> typedQuery = entityManager.createQuery(query);
		return typedQuery.getResultList();
	}

	public LocalDate formatDate(String queryDate) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate responseDate = LocalDate.parse(queryDate, dtf);
			return responseDate;
		} catch (Exception e) {
			return null;
		}
	}
}