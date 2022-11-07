package be.thomasmore.bookserver.model.converters;

import be.thomasmore.bookserver.model.BookUserReview;
import be.thomasmore.bookserver.model.dto.BookUserReviewDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookUserReviewDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BookUserReviewDTO convertToDto(BookUserReview bookUserReview){return modelMapper.map(bookUserReview, BookUserReviewDTO.class);}
}
