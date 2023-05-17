package com.example.model;

import java.math.BigDecimal;

public class QueryEmp {
	String ename;
	String job;
	String dname;
	String startDate;
	String endDate;
	BigDecimal salUpLimit;
	BigDecimal salLowLimit;
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public BigDecimal getSalUpLimit() {
		return salUpLimit;
	}
	public void setSalUpLimit(BigDecimal salUpLimit) {
		this.salUpLimit = salUpLimit;
	}
	public BigDecimal getSalLowLimit() {
		return salLowLimit;
	}
	public void setSalLowLimit(BigDecimal salLowLimit) {
		this.salLowLimit = salLowLimit;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QueryEmp [ename=");
		builder.append(ename);
		builder.append(", job=");
		builder.append(job);
		builder.append(", dname=");
		builder.append(dname);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", SalUpLimit=");
		builder.append(salUpLimit);
		builder.append(", SalLowLimit=");
		builder.append(salLowLimit);
		builder.append("]");
		return builder.toString();
	}
	
	

}
