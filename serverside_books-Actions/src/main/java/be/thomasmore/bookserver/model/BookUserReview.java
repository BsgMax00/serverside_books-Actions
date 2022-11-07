package be.thomasmore.bookserver.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class BookUserReview {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookUserReview_generator")
    @SequenceGenerator(name = "bookUserReview_generator", sequenceName = "bookUserReview_seq", allocationSize = 1)
    @Id
    int id;

    String date;

    String review;

    @ManyToOne(fetch = FetchType.LAZY)
    Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    User user;

}
