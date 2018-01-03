package com.example.demo.filtering.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value="field1") // this is one of the way we can achive static filtering
public class StaticFilterSomeBean {

	private String field1;
	private String field2;
	@JsonIgnore  // this is another way we can achive static filtering
	private String field3;
	
	public StaticFilterSomeBean() {
		super();
	}

	public StaticFilterSomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	
}
