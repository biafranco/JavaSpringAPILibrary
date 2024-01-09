package com.librarylink.api.repository;

import com.librarylink.api.models.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliotecaRepository extends JpaRepository <Biblioteca, Long> {
}
