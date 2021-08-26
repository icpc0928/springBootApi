package com.leo.springboogapi.service;

import com.leo.springboogapi.domain.Book;
import com.leo.springboogapi.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository repository;

    @Override
    public List<Book> findAllBooks() {
        return repository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return repository.findById(id).orElse(new Book());
    }

    @Override
    public Book saveBook(Book book) {
        return repository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        return repository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllBooks() {
        deleteAllBooks();
    }
}
