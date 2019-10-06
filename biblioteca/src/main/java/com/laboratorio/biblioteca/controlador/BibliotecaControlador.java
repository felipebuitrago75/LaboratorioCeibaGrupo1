package com.laboratorio.biblioteca.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laboratorio.biblioteca.entidades.Libro;
import com.laboratorio.biblioteca.entidades.Prestamo;
import com.laboratorio.biblioteca.entidades.Usuario;
import com.laboratorio.biblioteca.servicio.BibliotecaServicio;

@RestController
public class BibliotecaControlador {
	@Autowired
	private BibliotecaServicio bibliotecaServicio;
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/validarUsuario")
	public Boolean consultarUsuario(Usuario usuario) {
		return bibliotecaServicio.validarUsuario(usuario);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/agregarLibro")
	public void agregarLibro(Libro libro) {
		bibliotecaServicio.agregarLibro(libro);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/eliminarLibro/{ISBN}")
	public void eliminarLibro(@PathVariable(name = "ISBN") Long Isbn) {
		bibliotecaServicio.eliminarLibro(Isbn);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/obtenerLibrosDisponibles")
	public List<Libro> obtenerLibrosDisponibles() {
		return bibliotecaServicio.obtenerLibrosDisponibles();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/buscarLibroIsbn/{ISBN}")
	public Libro buscarLibroIsbn(@PathVariable(name = "ISBN") Long isbn) {
		return bibliotecaServicio.buscarLibroIsbn(isbn);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/prestarLibro/{ISBN}/{NOMBRE}")
	public void agregarPrestamo(@PathVariable(name = "ISBN") Long isbn, @PathVariable(name = "NOMBRE") String nombre) {
		bibliotecaServicio.prestarLibro(isbn, nombre);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/buscarLibrosPrestados")
	public List<Prestamo> buscarLibrosPrestados() {
		return bibliotecaServicio.buscarLibrosPrestados();
	}
}
