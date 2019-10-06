package com.laboratorio.biblioteca.servicio.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laboratorio.biblioteca.entidades.Libro;
import com.laboratorio.biblioteca.entidades.Prestamo;
import com.laboratorio.biblioteca.entidades.Usuario;
import com.laboratorio.biblioteca.repositorio.LibroRepositorio;
import com.laboratorio.biblioteca.repositorio.PrestamoRepositorio;
import com.laboratorio.biblioteca.repositorio.UsuarioRepositorio;
import com.laboratorio.biblioteca.servicio.BibliotecaServicio;

@Service
public class BibliotecaServicioImpl implements BibliotecaServicio {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private LibroRepositorio libroRepositorio;
	@Autowired
	private PrestamoRepositorio prestamoRepositorio;
	@PersistenceContext
	private EntityManager entityManager;

	public Boolean validarUsuario(Usuario usuario) {
		List<Usuario> usuarios = usuarioRepositorio.findAll();
		for (Usuario usuarioBD : usuarios) {
			if (usuarioBD.getNombreUsuario().equalsIgnoreCase(usuario.getNombreUsuario())
					&& usuarioBD.getContrasenia().equals(usuario.getContrasenia())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Libro> obtenerLibrosDisponibles() {
		String consultaLibrosDisponibles = "SELECT l FROM Libro l WHERE l.cantidadDisponible > 0";
		return entityManager.createQuery(consultaLibrosDisponibles).getResultList();
	}

	@Override
	public void agregarLibro(Libro libro) {

		libroRepositorio.save(libro);
	}

	@Override
	public void eliminarLibro(Long Isbn) {
		libroRepositorio.deleteById(Isbn);
	}

	@Override
	public Libro buscarLibroIsbn(Long isbn) {
		try {
			Optional<Libro> optLibro = libroRepositorio.findById(isbn);
			return optLibro.get();
		} catch (NoSuchElementException nse) {
			return null;
		}

	}

	@Override
	public void agregarPrestamo(Prestamo prestamo) {
		prestamoRepositorio.save(prestamo);

	}

}
