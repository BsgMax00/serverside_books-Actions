package be.thomasmore.bookserver.model.dto;

import be.thomasmore.bookserver.model.Book;
import be.thomasmore.bookserver.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class BookUserReviewDTO {
    private int id;
    private String date;
    private String review;
    private BookDTO book;
    private UserDTO user;
}
