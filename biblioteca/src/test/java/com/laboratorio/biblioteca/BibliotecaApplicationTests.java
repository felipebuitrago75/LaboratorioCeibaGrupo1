package com.laboratorio.biblioteca;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.laboratorio.biblioteca.entidades.Libro;
import com.laboratorio.biblioteca.entidades.Prestamo;
import com.laboratorio.biblioteca.entidades.PrestamoException;
import com.laboratorio.biblioteca.repositorio.PrestamoRepositorio;
import com.laboratorio.biblioteca.servicio.impl.BibliotecaServicioImpl;

@RunWith(SpringRunner.class)
@AutoConfigureTestEntityManager
@Transactional
@SpringBootTest
public class BibliotecaApplicationTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private BibliotecaServicioImpl biblioteca;

	@Autowired
	private PrestamoRepositorio prestamoRepo;

	@Test
	public void guardarLibro() {
		Libro libro = new Libro();
		libro.setIsbn(232342L);
		libro.setCantidadDisponible(1);
		libro.setCantidadInventario(1);
		libro.setNombre("Prueba");
		biblioteca.agregarLibro(libro);

		Libro libroEncontrado = entityManager.find(Libro.class, 232342L);

		assertEquals(libro.getNombre(), libroEncontrado.getNombre());
	}

	@Test
	public void eliminarLibro() {
		Libro libro = new Libro();
		libro.setIsbn(232342L);
		libro.setCantidadDisponible(1);
		libro.setCantidadInventario(1);
		libro.setNombre("Prueba");
		biblioteca.agregarLibro(libro);

		biblioteca.eliminarLibro(libro.getIsbn());

		Libro libroEncontrado = entityManager.find(Libro.class, 232342L);

		assertNull(libroEncontrado);
	}

	@Test
	public void buscarLibro() {
		Libro libro = new Libro();
		libro.setIsbn(232342L);
		libro.setCantidadDisponible(1);
		libro.setCantidadInventario(1);
		libro.setNombre("Prueba");
		biblioteca.agregarLibro(libro);

		Libro libroEncontrado = biblioteca.buscarLibroIsbn(232342L);

		assertEquals(libro.getNombre(), libroEncontrado.getNombre());
	}

	@Test
	public void consultarLibrosDisponibles() {
		Libro libro = new Libro();
		libro.setIsbn(232342L);
		libro.setCantidadDisponible(1);
		libro.setCantidadInventario(1);
		libro.setNombre("Prueba");
		biblioteca.agregarLibro(libro);

		List<Libro> libros = biblioteca.obtenerLibrosDisponibles();

		assertTrue(libros.size() > 0);
		assertEquals(libros.get(0).getNombre(), libro.getNombre());
	}

	@Test
	public void esPalindormaTrue() {
		Libro libro = new Libro();
		libro.setIsbn(232342L);
		libro.setCantidadDisponible(1);
		libro.setCantidadInventario(1);
		libro.setNombre("Prueba");
		biblioteca.agregarLibro(libro);

		boolean esPalindroma = biblioteca.esPalindromo("12321");

		assertTrue(esPalindroma);
	}

	@Test
	public void esPalindormaFalse() {
		Libro libro = new Libro();
		libro.setIsbn(232342L);
		libro.setCantidadDisponible(1);
		libro.setCantidadInventario(1);
		libro.setNombre("Prueba");
		biblioteca.agregarLibro(libro);

		boolean esPalindroma = biblioteca.esPalindromo("14721");

		assertTrue(!esPalindroma);
	}

	@Test(expected = PrestamoException.class)
	public void errorPalindromo() {
		Libro libro = new Libro();
		libro.setIsbn(23232L);
		libro.setCantidadDisponible(1);
		libro.setCantidadInventario(1);
		libro.setNombre("Prueba");
		biblioteca.agregarLibro(libro);

		biblioteca.prestarLibro(23232L, "Jhon Lara");
	}

	@Test(expected = PrestamoException.class)
	public void errorLibroInexistente() {
		biblioteca.prestarLibro(232565L, "Jhon Lara");
	}

	@Test(expected = PrestamoException.class)
	public void errorSinDisponibilidad() {
		Libro libro = new Libro();
		libro.setIsbn(2322L);
		libro.setCantidadDisponible(0);
		libro.setCantidadInventario(1);
		libro.setNombre("Prueba");
		biblioteca.agregarLibro(libro);

		biblioteca.prestarLibro(2322L, "Jhon Lara");
	}

	@Test
	public void guardarPrestamo() {
		Libro libro = new Libro();
		libro.setIsbn(2322L);
		libro.setCantidadDisponible(1);
		libro.setCantidadInventario(1);
		libro.setNombre("Prueba");
		biblioteca.agregarLibro(libro);

		biblioteca.prestarLibro(2322L, "Jhon Lara");

		Prestamo prestamo = prestamoRepo.findAll().get(0);

		assertEquals(prestamo.getISBN().toString(), "2322");
	}

}
