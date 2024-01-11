package com.librarylink.api.repository;

import com.librarylink.api.models.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BibliotecaRepository extends JpaRepository <Biblioteca, Long> {

}
