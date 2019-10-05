package com.laboratorio.biblioteca.entidades;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase encargada de modelar el objeto prestamo
 * 
 * @author JhonLara
 *
 */
@Entity
@Table(name = "PRESTAMO")
public class Prestamo {

	/**
	 * Atributo que representa el identificador del prestamo
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPrestamo;

	/**
	 * Atributo que representa el libre del prestamo
	 */
	@ManyToOne(optional = false)
	private Libro libro;

	/**
	 * Atributo que representa la fecha en que se realizo el prestamo
	 */
	@Column(name = "FECHA_SOLICITUD")
	private Date fechaSolicitud;
	/**
	 * Atributo que representa la fecha de entrega del prestamo
	 */
	@Column(name = "FECHA_ENTREGA")
	private Date fechaEntrega;
	/**
	 * Atributo que representa el nombre del usuario del prestamo
	 */
	@Column(name = "NOMBRE_USUARIO")
	private String nombreUsuario;

	/**
	 * Atributo que representa los prestamos que tiene el libro
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "idPrestamo")
	private ArrayList<Prestamo> prestamos;

	/**
	 * @param idPrestamo
	 * @param fechaSolicitud
	 * @param fechaEntrega
	 * @param nombreUsuario
	 */
	public Prestamo(Libro libro, Date fechaSolicitud, Date fechaEntrega, String nombreUsuario) {
		super();
		this.libro = libro;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaEntrega = fechaEntrega;
		this.nombreUsuario = nombreUsuario;
	}

	/**
	 * 
	 */
	public Prestamo() {
		super();
	}

	/**
	 * @return the idPrestamo
	 */
	public int getIdPrestamo() {
		return idPrestamo;
	}

	/**
	 * @param idPrestamo the idPrestamo to set
	 */
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	/**
	 * @return the libro
	 */
	public Libro getLibro() {
		return libro;
	}

	/**
	 * @param libro the libro to set
	 */
	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	/**
	 * @return the fechaSolicitud
	 */
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * @param fechaSolicitud the fechaSolicitud to set
	 */
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	/**
	 * @return the fechaEntrega
	 */
	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * @param fechaEntrega the fechaEntrega to set
	 */
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * @return the nombreUsuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * @param nombreUsuario the nombreUsuario to set
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

}