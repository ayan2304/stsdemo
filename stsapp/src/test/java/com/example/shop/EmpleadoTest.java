package com.example.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.app.entity.Empleado;
import com.example.app.repository.EmpleadoRepository;
import com.example.app.service.impl.EmpleadoServiceImpl;

@SpringBootTest
public class EmpleadoTest {

	@Autowired
	private EmpleadoServiceImpl emplServ;

	@MockBean
	private EmpleadoRepository emplRepo;

	@Test
	public void getEmpleados() {
		when(emplRepo.findAll()).thenReturn(Stream
				.of(new Empleado(null, "Ayan", "Mameyev", "ss", 23)).collect(Collectors.toList()));
		assertEquals(2, emplServ.getAll());
	}

}
