package com.example.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.example.app.entity.Empleado;
import com.example.app.repository.EmpleadoRepository;
import com.example.app.service.EmpleadoService;

@Service
@Qualifier("SPAIN")
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private final EmpleadoRepository emplRepo;
	
	public EmpleadoServiceImpl(EmpleadoRepository emplRepo) {
		super();
		this.emplRepo = emplRepo;
	}

	@Override
	public List<Empleado> getAll() {

		List<Empleado> lista = emplRepo.findAll();
		System.out.println("DATOS :" + lista);
		
		return lista;
	}

	@Override
	public Empleado saveEmpleado(Empleado empleado) {
		//empleado.setCargo("SPAIN");
		System.out.println("ANADIR" + empleado);
		return emplRepo.save(empleado);
	}
	
	@Override
	public void deleteEmpleadoById(Long id) {
		emplRepo.deleteById(id);
	}

	@Override
	public Empleado getEmpleadoById(Long id) {
		return emplRepo.findById(id).get();
	}

	@Override
	public Empleado editEmpleado(Empleado empleado) {
		return emplRepo.save(empleado);
	}

}
