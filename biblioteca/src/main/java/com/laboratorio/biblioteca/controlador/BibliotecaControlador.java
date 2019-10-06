package com.laboratorio.biblioteca.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laboratorio.biblioteca.entidades.Libro;
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

	@PostMapping("/agregarLibro")
	public void agregarLibro(Libro libro) {
		bibliotecaServicio.agregarLibro(libro);
	}

	@DeleteMapping("/eliminarLibro/{ISBN}")
	public void eliminarLibro(@PathVariable(name = "ISBN") Long Isbn) {
		bibliotecaServicio.eliminarLibro(Isbn);
	}

	@GetMapping("/obtenerLibroPorIsbn/{ISBN}")
	@Query(value = "SELECT l FROM Libro l WHERE l.isbn IN :isbn")
	public Libro obtenerLibroPorIsbn(@PathVariable(name = "ISBN") String Isbn) {
		return bibliotecaServicio.obtenerLibroPorIsbn(Isbn);
	}

}
