package com.example.demo.user;

import com.example.demo.BaseController;
import com.example.demo.books.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
public class UserController extends BaseController {
    @Autowired
    private IUserRepository iUserRepository;

    @PostMapping("/users")
    public User create(@Valid @RequestBody User user) {
        return iUserRepository.save(user);
    }

    @PutMapping("/users/{user_id}")
    public User put(@PathVariable("user_id") int userId, @RequestBody List<Integer> booksIds) {
        User user = iUserRepository.findById(userId).get();
        List<Book> books = new LinkedList<>();
        booksIds.forEach(bookId -> {
            Book book = new Book();
            book.setId(bookId);
            books.add(book);
        });

        user.setBooks(books);
        iUserRepository.save(user);

        return user;
    }

    @GetMapping("/users")
    public List<User> get() {
        return iUserRepository.findAll();
    }
}
