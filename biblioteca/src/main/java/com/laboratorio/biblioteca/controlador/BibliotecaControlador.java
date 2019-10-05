package com.laboratorio.biblioteca.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laboratorio.biblioteca.entidades.Usuario;
import com.laboratorio.biblioteca.servicio.BibliotecaServicio;

@RestController
public class BibliotecaControlador {
	@Autowired
	private BibliotecaServicio bibliotecaServicio;

	@GetMapping("/validarUsuario")
	public Boolean consultarUsuario(Usuario usuario) {
		return bibliotecaServicio.validarUsuario(usuario);
	}
}
