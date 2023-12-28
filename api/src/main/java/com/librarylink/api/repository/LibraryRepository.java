package com.librarylink.api.repository;

import com.librarylink.api.repository.BookRepository;
import com.librarylink.api.models.Book;
import com.librarylink.api.models.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public interface LibraryRepository extends JpaRepository<Library, Long> {
    //public static List<Book> findBooksByLibraryCode(int libraryCode) {}
}
