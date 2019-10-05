package com.laboratorio.biblioteca.entidades;

import java.util.Date;

import com.laboratorio.biblioteca.BibliotecaApplication;

public class Prestamo {

	private Libro libro;
	private Date fechaSolicitud;
	private Date fechaEntrega;
	private String nombreUsuario;
	private Biblioteca biblioteca;

	public Prestamo(Libro libro) {
		this.fechaSolicitud = new Date();
		this.libro = libro;
	}

	public Prestamo(Libro libro, Date fechaSolicitud, Date fechaEntrega, String nombreUsuario, Biblioteca biblioteca) {

		this.libro = libro;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaEntrega = fechaEntrega;
		this.nombreUsuario = nombreUsuario;
		this.biblioteca = biblioteca;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public Libro getLibro() {
		return libro;
	}

	public Date getFechaEntregaMaxima() {
		return fechaEntregaMaxima;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

}
