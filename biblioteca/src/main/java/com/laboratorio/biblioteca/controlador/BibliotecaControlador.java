package com.laboratorio.biblioteca.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laboratorio.biblioteca.entidades.Usuario;
import com.laboratorio.biblioteca.servicio.BibliotecaServicio;

@RestController
public class BibliotecaControlador {
	@Autowired
	private BibliotecaServicio bibliotecaServicio;

	@PostMapping("/api/employees")
	public Boolean saveEmployee(Usuario usuario) {
		return bibliotecaServicio.validarUsuario(usuario);
	}
}
