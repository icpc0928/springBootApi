package com.leo.springboogapi.dto;

import com.leo.springboogapi.domain.Book;
import com.leo.springboogapi.util.CustomBeanUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BookDTO {

    @NotBlank
    private String author;
    @Length(max = 200)
    private String description;
    @NotNull
    private String isbn;
    @NotBlank
    private String name;
    @NotNull
    private int status;

    BookDTO(){

    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", isbn='" + isbn + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * 轉換傳輸對象
     * @param book
     */
    public void convertToBook(Book book){
        new BookConvert().convert(this, book);
    }

    public Book convertToBook(){
        return new BookConvert().convert(this);
    }


    private class BookConvert implements Convert<BookDTO, Book>{

        @Override           //S                T
        public Book convert(BookDTO bookDTO, Book book) {
            String[] nullPropertyNames = CustomBeanUtils.getNullPropertyNames(bookDTO);
            BeanUtils.copyProperties(bookDTO, book, nullPropertyNames); //濾掉null值
            return book;
        }

        @Override
        public Book convert(BookDTO bookDTO) {
            Book book = new Book();
            BeanUtils.copyProperties(bookDTO, book); //濾掉null值
            return book;
        }
    }
}
