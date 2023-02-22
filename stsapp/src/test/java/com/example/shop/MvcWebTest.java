package com.example.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.app.entity.Empleado;
import com.example.app.repository.EmpleadoRepository;
import com.example.app.service.impl.EmpleadoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class MvcWebTest {
	
	@Autowired
	EmpleadoServiceImpl emplServ;
	
	@MockBean
	EmpleadoRepository emplRepo;

	@Test
	public void getLista() throws Exception {
		
		Mockito.when(emplRepo.findAll()).thenReturn(Stream
				.of(new Empleado(1L, "Ayan", "Mameyev", "ss", 23),
					new Empleado(2L, "Test", "test", "dd", 45))
				.collect(Collectors.toList()));
		assertEquals(2, emplServ.getAll().size());
		
	}

	@Test
	public void addEmpleado() throws Exception {
		
		Empleado empl = new Empleado(3L, "AAA", "AAA", "AAA", 23);
		
		Mockito.when(emplRepo.save(Mockito.any(Empleado.class))).thenReturn(empl);
		assertEquals(empl, emplServ.saveEmpleado(empl));

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}



