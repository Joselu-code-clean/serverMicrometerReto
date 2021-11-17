package com.example.serverMicrometerReto.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
@Endpoint(id = "estados")
public class StatusController {
	
	
	private List<String> listado = new ArrayList<>();
	private Counter counter;

	public StatusController(MeterRegistry registry) {
		this.counter = Counter.builder("Estados:").description("Estados modificados ---").register(registry);
	}
	
	public StatusController() {
		// TODO Auto-generated constructor stub
	}

	@ReadOperation
	public List<String> estados(){
		return listado;
	}
	
	@WriteOperation
	public void writeOperation(@Selector String estadoNuevo) {
		counter.increment();
		listado.add(estadoNuevo);
	}
	
	@DeleteOperation
	public void deleteOperation(@Selector int estadoBorrar) {
		listado.remove(estadoBorrar);
	}
	
}
