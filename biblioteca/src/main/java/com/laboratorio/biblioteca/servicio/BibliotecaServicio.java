package com.laboratorio.biblioteca.servicio;

import java.util.List;

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
	public List<Libro> obtenerLibrosDisponibles();
}
