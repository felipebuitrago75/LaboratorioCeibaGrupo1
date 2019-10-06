package com.laboratorio.biblioteca.servicio;

import java.util.List;

import com.laboratorio.biblioteca.entidades.Libro;
import com.laboratorio.biblioteca.entidades.Prestamo;
import com.laboratorio.biblioteca.entidades.Usuario;

public interface BibliotecaServicio {

	public Boolean validarUsuario(Usuario usuario);

	public void agregarLibro(Libro libro);

	public void eliminarLibro(Long Isbn);

	public Libro buscarLibroIsbn(Long isbn);

	/**
	 * Método encargado de obtener un libro por isbn
	 * 
	 * @author JhonLara
	 */
	public List<Libro> obtenerLibrosDisponibles();

	/**
	 * Método encargado de realizar el prestamo del libro
	 * 
	 * @author JhonLara
	 */
	public void prestarLibro(Long isbn, String nombre);
	
	public List<Prestamo> buscarLibrosPrestados();
	
}
