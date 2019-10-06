package com.laboratorio.biblioteca.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laboratorio.biblioteca.entidades.Prestamo;

/**
 * Se establece repositorio para la entidad prestamo 
 * 
 * @author Andrés Felipe
 *
 */
@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo, Long> {

}
