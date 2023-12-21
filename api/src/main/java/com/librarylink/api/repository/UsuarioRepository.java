package com.librarylink.api.repository;

import com.librarylink.api.models.Book;
import com.librarylink.api.models.Library;
import com.librarylink.api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
