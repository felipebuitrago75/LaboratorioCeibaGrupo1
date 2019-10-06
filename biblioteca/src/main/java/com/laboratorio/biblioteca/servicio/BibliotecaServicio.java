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

	public void agregarPrestamo(Prestamo prestamo);
}
