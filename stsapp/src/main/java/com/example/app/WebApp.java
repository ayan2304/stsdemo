package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.app.entity.Empleado;
import com.example.app.repository.EmpleadoRepository;

@SpringBootApplication
public class WebApp  implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(WebApp.class, args);
	}

	@Autowired
	private EmpleadoRepository emplRepo;

	public void run(String... args) throws Exception {
		Empleado empl1 = new Empleado(null, "Ayan", "Mameyev", "ss", 23);
		emplRepo.save(empl1);
		
		Empleado empl2 = new Empleado(null, "Test", "test", "dd", 45);
		emplRepo.save(empl2);

	}

}
