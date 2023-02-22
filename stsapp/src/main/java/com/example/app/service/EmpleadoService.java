package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.entity.Empleado;

public interface EmpleadoService {
	
	List<Empleado> getAll();

	Empleado saveEmpleado(Empleado empleado);

	Empleado getEmpleadoById(Long id);
	Empleado editEmpleado(Empleado empleado);
	
	void deleteEmpleadoById(Long id);

}
