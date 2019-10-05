package com.laboratorio.biblioteca.servicio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.laboratorio.biblioteca.entidades.Libro;
import com.laboratorio.biblioteca.entidades.Usuario;

public interface BibliotecaServicio {

	public Boolean validarUsuario(Usuario usuarioS);

	/**
	 * Método encargado de obtener un libro por isbn
	 * 
	 * @author JhonLara
	 */
	@Query(value = "SELECT l FROM Libro l WHERE l.isbn IN :isbn")
	public Libro obtenerLibroPorIsbn(@Param("isbn") String isbn);
}
