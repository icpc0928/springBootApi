package com.leo.springboogapi.api;

import com.leo.springboogapi.domain.Book;
import com.leo.springboogapi.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//外部控制器
@RestController
@RequestMapping("/api/v1")
public class BookApi {

    @Autowired
    private BookService bookService;

    /**
     * 獲取書單列表 + 狀態
     * @return
     */
    @GetMapping("/books")
    public ResponseEntity<?> listAllBooks(){
        List<Book> books = bookService.findAllBooks();
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    /**
     * 獲取一個書單
     * @param id
     * @return
     */
    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id){
        Book book = bookService.getBookById(id);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<?> saveBook(@RequestBody Book book){   //默認使用Form-data 傳輸, 但前端可能會使用json格式,所以使用 @RequestBody 接收JSON對象
        Book book1 = bookService.saveBook(book);
        //新增成功返回201(CREATED)
        return new ResponseEntity<Object>(book1, HttpStatus.CREATED);
    }

    /**
     * 更新一個書單
     * @param id
     * @param book
     * @return
     */
    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, Book book){
        Book currentBook = bookService.getBookById(id);
        BeanUtils.copyProperties(book, currentBook);
        Book book1 = bookService.updateBook(currentBook);
        return new ResponseEntity<>(book1, HttpStatus.OK);
    }

    /**
     * 刪除一筆書單
     * @param id
     * @return
     */
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        bookService.deleteBookById(id);
        //刪除成功使用202 NO Content
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * 刪除所有書單
     * @return
     */
    @DeleteMapping("/books")
    public ResponseEntity<?> deleteAllBooks(){
        bookService.deleteAllBooks();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
