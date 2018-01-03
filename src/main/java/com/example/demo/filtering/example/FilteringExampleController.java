package com.example.demo.filtering.example;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringExampleController {
	
	/**
	 * Example of Static Filtering
	 * @return
	 */

	@GetMapping("/static-filter")
	public StaticFilterSomeBean filterBean(){
		return new StaticFilterSomeBean("value1","value2","value3");
	}
	
	
	/*
	 * Example for Dynamic Filtering
	 */
	@GetMapping("/dynamic-filter")
	public MappingJacksonValue dynamicFilterBean(){
		
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicFilter", filter);
		
		DynamicFilterSomeBean dBean = new DynamicFilterSomeBean("value1","value2","value3");
		MappingJacksonValue mapping = new MappingJacksonValue(dBean);
		
		mapping.setFilters(filters);
		
		return mapping;
	}
}
