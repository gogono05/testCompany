package com.example.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "dept2")
public class Dept {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long deptno;
	private String dname;
	private String loc;

	@OneToMany(mappedBy = "deptno", cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonManagedReference
	@JsonIgnore
	private Set<Emp> emps;

	public Long getDeptno() {
		return deptno;
	}



	public void setDdeptno(Long deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Dept2 [deptno=");
		builder.append(deptno);
		builder.append(", dname=");
		builder.append(dname);
		builder.append(", loc=");
		builder.append(loc);
		builder.append("]");
		return builder.toString();
	}

}
