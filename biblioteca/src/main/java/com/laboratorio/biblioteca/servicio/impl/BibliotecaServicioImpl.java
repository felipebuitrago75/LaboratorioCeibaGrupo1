package com.laboratorio.biblioteca.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laboratorio.biblioteca.entidades.Libro;
import com.laboratorio.biblioteca.entidades.Usuario;
import com.laboratorio.biblioteca.repositorio.BibliotecaRepositorio;
import com.laboratorio.biblioteca.servicio.BibliotecaServicio;

@Service
public class BibliotecaServicioImpl implements BibliotecaServicio {

	@Autowired
	private BibliotecaRepositorio bibliotecaRepositorio;

	@PersistenceContext
	private EntityManager entityManager;

	public Boolean validarUsuario(Usuario usuario) {
		List<Usuario> usuarios = bibliotecaRepositorio.findAll();
		for (Usuario usuarioBD : usuarios) {
			if (usuarioBD.getNombreUsuario().equalsIgnoreCase(usuario.getNombreUsuario())
					&& usuarioBD.getContrasenia().equals(usuario.getContrasenia())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Libro obtenerLibroPorIsbn(String isbn) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Libro> query = cb.createQuery(Libro.class);
		Root<Libro> libro = query.from(Libro.class);

		Path<String> isbnPath = libro.get("isbn");
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(cb.like(isbnPath, isbn));

		query.select(libro).where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

		return (Libro) entityManager.createQuery(query).getResultList();
	}

}
