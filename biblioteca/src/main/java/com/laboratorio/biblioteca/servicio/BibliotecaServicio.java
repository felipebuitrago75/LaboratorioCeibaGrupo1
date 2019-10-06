package com.laboratorio.biblioteca.servicio;

import com.laboratorio.biblioteca.entidades.Libro;
import com.laboratorio.biblioteca.entidades.Usuario;

public interface BibliotecaServicio {

	public Boolean validarUsuario(Usuario usuarioS);

	public void agregarLibro(Libro libro);

	public void eliminarLibro(Long Isbn);

	/**
	 * Método encargado de obtener un libro por isbn
	 * 
	 * @author JhonLara
	 */
	public Libro obtenerLibroPorIsbn(Long isbn);

	/**
	 * Método encargado de realizar el prestamo del libro
	 * 
	 * @author JhonLara
	 */
	public void prestarLibro(Long isbn, String nombre);
}
