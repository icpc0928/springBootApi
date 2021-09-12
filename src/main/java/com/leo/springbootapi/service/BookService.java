package com.leo.springbootapi.service;

import com.leo.springbootapi.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> findAllBooks();

    Book getBookById(Long id);

    Book saveBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);

    void deleteAllBooks();

}
