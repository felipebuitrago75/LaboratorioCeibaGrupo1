package com.laboratorio.biblioteca.entidades;

/**
 * Clase encargada de modelar el objeto libro
 * 
 * @author Grupo1
 *
 */
public class Libro {

	private int idLibro;
	/**
	 * Atributo que representa el isbn del libro
	 */
	private String isbn;
	/**
	 * Atributo que representa el nombre del libro
	 */
	private String nombre;
	/**
	 * Atributo que representa la cantidad disponible del libro
	 */
	private int cantidadInventario;

	/**
	 * Atributo que representa la cantidad disponible del libro
	 */
	private int cantidadDisponible;

	public Libro(String isbn, String nombre, int cantidadInventario, int cantidadDisponible) {

		this.isbn = isbn;
		this.nombre = nombre;
		this.cantidadInventario = cantidadInventario;
		this.cantidadDisponible = cantidadDisponible;

	}

	/**
	 * Método encargado de obtener el valor del nombre
	 * 
	 * @return nombre el valor el nombre del libro
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método encargado de obtener el valor del nombre
	 * 
	 * @return nombre el valor el nombre del libro
	 */
	public int getCantidadInventario() {
		return cantidadInventario;
	}

	/**
	 * Método encargado de obtener el valor de la cantidad disponible del libro
	 * 
	 * @return nombre el valor el nombre del libro
	 */
	public int getCantidadDisponible() {
		return cantidadInventario;
	}

	/**
	 * Método encargado de obtener el valor del isbn
	 * 
	 * @return nombre el valor el isbn del libro
	 */
	public String getIsbn() {
		return isbn;
	}

}
