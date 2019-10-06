package com.laboratorio.biblioteca.servicio.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laboratorio.biblioteca.entidades.Libro;
import com.laboratorio.biblioteca.entidades.Prestamo;
import com.laboratorio.biblioteca.entidades.PrestamoException;
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

	public static final String EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE = "El libro no se encuentra disponible";
	public static final String PALIDROMO = "los libros pal?ndromos solo se pueden utilizar en la biblioteca";
	public static final String PRESTADO = "No hay libros disponibles para prestar";
	public static final String NO_EXISTE = "El libro no existe";

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

		try {
			Optional<Libro> optLibro = libroRepositorio.findById(libro.getIsbn());
			Libro libroActualizar = optLibro.get();
			libroActualizar.setCantidadDisponible(libro.getCantidadDisponible() + 1);
			libroActualizar.setCantidadInventario(libro.getCantidadInventario() + 1);
			libroRepositorio.save(libroActualizar);
		} catch (NoSuchElementException nse) {

			libroRepositorio.save(libro);
		}

	}

	@Override
	public void eliminarLibro(Long Isbn) {
		libroRepositorio.deleteById(Isbn);
	}

	@Override
	public void prestarLibro(Long isbn, String nombre) {
		// Variable
		boolean palindromo = false;
		Libro libro = buscarLibroIsbn(isbn);
		// Se valida si el libro existe
		if (libro != null) {

			boolean prestado = validarLibrosDisponibles(libro);
			// Se valida si el libro ya se encuentra en prestamo
			if (prestado) {
				// Se asigna el valor que devuelve el metodo de verificaci?n
				palindromo = esPalindromo(isbn.toString());
				if (palindromo == true) {
					throw new PrestamoException(PALIDROMO);
				} else {
					Date fechaMaximaEntrega = validarFechaIsbn(isbn);
					Prestamo prestamo = new Prestamo(libro.getIsbn(), new Date(), fechaMaximaEntrega, nombre);
					prestamoRepositorio.save(prestamo);
					
					libro.setCantidadDisponible(libro.getCantidadDisponible() - 1);
					libroRepositorio.save(libro);
				}
			} else {

				throw new PrestamoException(PRESTADO);
			}
		} else {
			throw new PrestamoException(NO_EXISTE);
		}

	}

	public boolean validarLibrosDisponibles(Libro libroIsbnprestado) {
		if (libroIsbnprestado != null && libroIsbnprestado.getCantidadDisponible() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * M?todo encargado de determinar si una palabra es o no palindroma
	 * 
	 * @param isbn isbn que se va a verificar
	 * @return si la cadena es o no palindroma
	 */
	public boolean esPalindromo(String isbn) {

		// Variable con el valor a retornar incial
		boolean valor = true;
		int i, indice;
		String cadenaIsbn = "";
		for (int x = 0; x < isbn.length(); x++) {
			if (isbn.charAt(x) != ' ')
				cadenaIsbn += isbn.charAt(x);
		}
		isbn = cadenaIsbn;
		indice = isbn.length();
		// Se recorre el arreglo de caracteres de adelante hacia atras y de atras hacia
		// adelante
		for (i = 0; i < (isbn.length()); i++) {
			if (isbn.substring(i, i + 1).equals(isbn.substring(indice - 1, indice)) == false) {
				valor = false;
				break;
			}
			indice--;
		}
		return valor;
	}

	private Date validarFechaIsbn(Long isbn) {
		String variable = "";
		int resultado = 0;

		for (int i = 0; i < isbn.toString().length(); i++) {
			char caracter = isbn.toString().charAt(i);
			if (Character.isDigit(caracter)) {
				variable = String.valueOf(caracter);
				resultado += Integer.parseInt(variable);
			}
		}
		if (resultado > 30) {
			return obtenerFecha();
		} else {
			return null;
		}
	}

	/**
	 * M?todo encargado de obtener la fecha m?xima
	 * 
	 * @return la fecha m?xima de entrega
	 */
	public Date obtenerFecha() {
		LocalDate resultado = LocalDate.now();
		int addedDays = 0;
		while (addedDays < 15) {
			resultado = resultado.plusDays(1);
			if ( resultado.getDayOfWeek() != DayOfWeek.SUNDAY) {
				++addedDays;
			}
		}

		return Date.from(resultado.atStartOfDay(ZoneId.systemDefault()).toInstant());
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
	public List<Prestamo> buscarLibrosPrestados() {
		List<Prestamo> librosPrestados = prestamoRepositorio.findAll();
		return librosPrestados;
	}

}
