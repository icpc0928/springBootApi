package com.leo.springboogapi.service;

import com.leo.springboogapi.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> findAllBooks();

    Book getBookById(Long id);

    Book saveBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);

    void deleteAllBooks();

}
