package be.thomasmore.bookserver.services;

import be.thomasmore.bookserver.model.Book;
import be.thomasmore.bookserver.model.BookUserReview;
import be.thomasmore.bookserver.model.converters.BookUserReviewDTOConverter;
import be.thomasmore.bookserver.model.dto.BookUserReviewDTO;
import be.thomasmore.bookserver.repositories.BookRepository;
import be.thomasmore.bookserver.repositories.BookUserReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookUserReviewService {
    @Autowired
    private BookUserReviewRepository bookUserReviewRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookUserReviewDTOConverter bookUserReviewDTOConverter;

    public List<BookUserReviewDTO> findAll(String titleKeyWord){
        final List<BookUserReview> bookUserReviews = bookUserReviewRepository.findAll();

        return bookUserReviews.stream()
                .map(b -> bookUserReviewDTOConverter.convertToDto(b))
                .collect(Collectors.toList());
    }

    public List<BookUserReviewDTO> bookUserReviewsForBook(int bookId) {
        Optional<Book> bookFromDb = bookRepository.findById(bookId);
        if (bookFromDb.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("book with id %d not found.", bookId));

        final List<BookUserReview> bookUserReviews = bookUserReviewRepository.findByBook(bookFromDb);
        return bookUserReviews.stream()
                .map(b -> bookUserReviewDTOConverter.convertToDto(b))
                .collect(Collectors.toList());
    }
}
