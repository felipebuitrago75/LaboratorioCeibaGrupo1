package com.laboratorio.biblioteca.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laboratorio.biblioteca.entidades.Usuario;

@Repository
public interface BibliotecaRepositorio extends JpaRepository<Usuario, Long>{

}
