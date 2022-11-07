package be.thomasmore.bookserver.controllers;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import be.thomasmore.bookserver.model.Book;
import be.thomasmore.bookserver.model.dto.BookDetailedDTO;
import be.thomasmore.bookserver.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/books/create_2_books.sql")
@Sql(scripts = "/sql/books/clean_books.sql", executionPhase = AFTER_TEST_METHOD)
public class BookControllerUpdateBookTest extends AbstractIntegrationTest {


    @Autowired
    private BookRepository bookRepository;

    @Test
    @WithMockUser()
    public void updateBook() throws Exception {
        final String UPDATED_BOOK_TITLE = "It is simple to update a book";
        BookDetailedDTO updatedBookDto = BookDetailedDTO.builder()
                .title(UPDATED_BOOK_TITLE)
                .id(1)
                .build();

        mockMvc.perform(getMockRequestPutBooks(updatedBookDto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value(UPDATED_BOOK_TITLE))
                .andExpect(jsonPath("$.authors").isEmpty());

        Book loadedBook = bookRepository.findByTitle(UPDATED_BOOK_TITLE).orElseThrow();
        assertThat(loadedBook.getTitle()).isEqualTo(UPDATED_BOOK_TITLE);
    }

}
