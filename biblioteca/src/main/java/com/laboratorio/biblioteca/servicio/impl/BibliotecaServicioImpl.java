package com.laboratorio.biblioteca.servicio.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laboratorio.biblioteca.entidades.Libro;
import com.laboratorio.biblioteca.entidades.Prestamo;
import com.laboratorio.biblioteca.entidades.Usuario;
import com.laboratorio.biblioteca.repositorio.LibroRepositorio;
import com.laboratorio.biblioteca.repositorio.UsuarioRepositorio;
import com.laboratorio.biblioteca.servicio.BibliotecaServicio;

@Service
public class BibliotecaServicioImpl implements BibliotecaServicio {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private LibroRepositorio libroRepositorio;
	@PersistenceContext
	private EntityManager entityManager;

	public static final String EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE = "El libro no se encuentra disponible";
	public static final String PALIDROMO = "los libros pal�ndromos solo se pueden utilizar en la biblioteca";
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
		libroRepositorio.save(libro);
	}

	@Override
	public void eliminarLibro(Long Isbn) {
		libroRepositorio.deleteById(Isbn);
	}

	@Override
	public void prestarLibro(Long isbn, String nombre) {
		// Variable
		boolean palindromo = false;
		Libro libro = obtenerLibroPorIsbn(isbn);
		// Se valida si el libro existe
		if (libro != null) {

			boolean prestado = validarPrestamo(libro);
			// Se valida si el libro ya se encuentra en prestamo
			if (!prestado) {
				// Se asigna el valor que devuelve el metodo de verificaci�n
				palindromo = esPalindromo(isbn.toString());
				if (palindromo == true) {
					throw new UnsupportedOperationException(PALIDROMO);
				} else {
					Date fechaMaximaEntrega = validarFechaIsbn(isbn);
					Prestamo prestamo = new Prestamo(libro, new Date(), fechaMaximaEntrega, nombre);
					repositorioPrestamo.agregar(prestamo);
				}
			} else {

				throw new UnsupportedOperationException(PRESTADO);
			}
		} else {
			throw new UnsupportedOperationException(NO_EXISTE);
		}

	}

	public boolean validarPrestamo(Libro libroIsbnprestado) {
		if (libroIsbnprestado != null && libroIsbnprestado.getCantidadDisponible() > 0
				&& libroIsbnprestado.getCantidadInventario() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * M�todo encargado de determinar si una palabra es o no palindroma
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
	 * M�todo encargado de obtener la fecha m�xima
	 * 
	 * @return la fecha m�xima de entrega
	 */
	@SuppressWarnings("deprecation")
	private Date obtenerFecha() {
		Date FechaEjecucion = new Date();
		Date fechaDevolucion = new Date();
		int diferenciaDias = 0;

		int diasTotal = 0;
		while (diferenciaDias != 15) {

			if (FechaEjecucion.getDay() != 0 && FechaEjecucion.getDay() != 6) {
				diferenciaDias++;
			}
			FechaEjecucion.setDate(FechaEjecucion.getDate() + 1);
			diasTotal++;
		}
		fechaDevolucion.setDate(diasTotal);
		return fechaDevolucion;
	}
}
