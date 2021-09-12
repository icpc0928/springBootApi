package com.leo.springbootapi.api;

import com.leo.springbootapi.domain.Book;
import com.leo.springbootapi.dto.BookDTO;
import com.leo.springbootapi.exception.InvalidRequestException;
import com.leo.springbootapi.exception.NotFoundException;
import com.leo.springbootapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        if(books.isEmpty()){
            throw new NotFoundException("Not Found 書單列表不存在");
        }
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
        if(book == null){
            throw new NotFoundException(String.format("Book by id %s not found.", id));
        }
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    /**
     * 新增一條書單
     * @param bookDTO
     * @return
     */
    @PostMapping("/books")
    public ResponseEntity<?> saveBook(@Valid @RequestBody BookDTO bookDTO , BindingResult bindingResult){   //默認使用Form-data 傳輸, 但前端可能會使用json格式,所以使用 @RequestBody 接收JSON對象
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("Invalid parameter", bindingResult);
        }

        Book book1 = bookService.saveBook(bookDTO.convertToBook());
        //新增成功返回201(CREATED)
        return new ResponseEntity<Object>(book1, HttpStatus.CREATED);
    }

    /**
     * 更新一個書單,如帶入的BookDTO參數可能不完整,會所以新增convert方法,並且濾掉null的值, null的值由getNullPropertyNames取得
     * @param id
     * @param bookDTO
     * @return
     */
    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id,@Valid @RequestBody BookDTO bookDTO, BindingResult bindingResult){

        Book currentBook = bookService.getBookById(id);
        if(currentBook == null){
            throw new NotFoundException(String.format("Book by id %s not found.", id));
        }
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("Invalid parameter", bindingResult);
        }
        bookDTO.convertToBook(currentBook);
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
