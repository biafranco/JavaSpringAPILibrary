package com.librarylink.api.repository;

import com.librarylink.api.models.Book;
import com.librarylink.api.models.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}
