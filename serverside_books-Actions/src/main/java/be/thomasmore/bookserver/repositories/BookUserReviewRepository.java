package be.thomasmore.bookserver.repositories;

import be.thomasmore.bookserver.model.Book;
import be.thomasmore.bookserver.model.BookUserReview;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookUserReviewRepository extends CrudRepository<BookUserReview, Integer> {
    List<BookUserReview> findAll();

    List<BookUserReview> findByBook(Optional<Book> book);
}
