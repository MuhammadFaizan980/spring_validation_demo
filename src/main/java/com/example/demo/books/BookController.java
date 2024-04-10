package com.example.demo.books;

import com.example.demo.BaseController;
import com.example.demo.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
public class BookController extends BaseController {
    @Autowired
    private IBookRepository iBookRepository;

    @PostMapping("/books")
    public Book create(@Valid @RequestBody Book book) {
        return iBookRepository.save(book);
    }

    @PutMapping("/books/{book_id}")
    public Book put(@PathVariable("book_id") int bookId, @RequestBody List<Integer> userIds) {
        Book book = iBookRepository.findById(bookId).get();
        List<User> users = new LinkedList<>();
        userIds.forEach(uId -> {
            User user = new User();
            user.setId(uId);
            users.add(user);
        });

        book.setUsers(users);
        iBookRepository.save(book);

        return book;
    }

    @GetMapping("/books")
    public List<Book> get() {
        return iBookRepository.findAll();
    }
}
