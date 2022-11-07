package be.thomasmore.bookserver.controllers;

import be.thomasmore.bookserver.model.BookUserReview;
import be.thomasmore.bookserver.model.dto.BookUserReviewDTO;
import be.thomasmore.bookserver.services.BookUserReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class BookUserReviewController {
    @Autowired
    private BookUserReviewService bookUserReviewService;

    @GetMapping("books/{bookId}/reviews")
    public List<BookUserReviewDTO> findOneBook(@PathVariable int bookId){
        return bookUserReviewService.bookUserReviewsForBook(bookId);
    }

    @GetMapping("reviews")
    public List<BookUserReviewDTO> findALl(String titleKeyWord){
        return bookUserReviewService.findAll(titleKeyWord);
    }
}
