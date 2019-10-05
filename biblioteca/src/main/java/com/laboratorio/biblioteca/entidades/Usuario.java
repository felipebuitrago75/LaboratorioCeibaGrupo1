package com.laboratorio.biblioteca.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase encargada de modelar el objeto usuario
 * 
 * @author JhonLara
 *
 */
@Entity
@Table(name = "USUARIO")
public class Usuario {

	/**
	 * Atributo que representa el nombre del usuario
	 */
	@Column(name = "NOMBRE")
	private String nombreUsuario;
	/**
	 * Atributo que representa la contraseña del usuario
	 */
	@Column(name = "PASSWORD")
	private String contrasenia;

	/**
	 * Constructor de la calse Usuario
	 * 
	 * @param idUsuario     identificador del usuario
	 * @param nombreUsuario nombre del usuario
	 * @param contrasenia   contraseña del usuario
	 * @param biblioteca    biblioteca a la cual acude el usuario
	 */
	public Usuario(String nombreUsuario, String contrasenia) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.contrasenia = contrasenia;
	}

	/**
	 * Constructor sin parametros de la clase
	 */
	public Usuario() {
		super();
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

	/**
	 * @return the contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * @param contrasenia the contrasenia to set
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

}
