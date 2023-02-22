package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.entity.Empleado;
import com.example.app.service.EmpleadoService;

@Controller
@RequestMapping("/form")

public class EmpleadoController {

	@Autowired
	//@Qualifier("SPAIN")
	private EmpleadoService emplServ;

	private static String backURL = "redirect:/form/list";
// 	Constant Value
//	@Value("${titulo.valor}")
//	private String cabecera;

	// La lista de empleados
	@GetMapping("/list")
	public String listaEmpleados(Model model) {

		model.addAttribute("visible", false);
		model.addAttribute("list_empl", emplServ.getAll());
		//System.out.println("LISTAAAAA " + emplServ.getAll().toString());
		return "list";
	}
	
	// Anadir nuevo empleado
	@GetMapping("/list/add")
	public String createEmpleadoForm(Model model) {

		// Create student object from form to data
		Empleado empleado = new Empleado();
		model.addAttribute("empleado", empleado);
		return "create_empleado";
	}

	// Guardar nuevo empleado
	@PostMapping("/list")
	public String saveStudent(@ModelAttribute("empleado") Empleado empleado) {
		
		emplServ.saveEmpleado(empleado);		
		
		return "redirect:/form/list";
	}
	
	

	// Editar
	@PostMapping("/list/{id}")
	public String editEmpleado(@PathVariable Long id, @ModelAttribute("empleado") Empleado empleado, Model model) {

		Empleado existingEmpl = emplServ.getEmpleadoById(id);
		existingEmpl.setId(id);
		existingEmpl.setNombre(empleado.getNombre());
		existingEmpl.setApellido(empleado.getApellido());
		existingEmpl.setCargo(empleado.getCargo());
		existingEmpl.setEdad(empleado.getEdad());

		emplServ.editEmpleado(existingEmpl);

		return backURL;
	}

	@GetMapping("/list/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("empleado", emplServ.getEmpleadoById(id));
		return "edit_empleado";
	}

	// Borrar empleado
	@GetMapping("/list/del/{id}")
	public String deleteStudent(@PathVariable Long id) {
		emplServ.deleteEmpleadoById(id);

		return backURL;
	}

}
