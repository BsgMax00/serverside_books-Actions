package be.thomasmore.bookserver.controllers;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import be.thomasmore.bookserver.model.dto.BookDetailedDTO;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/books/create_2_books.sql")
@Sql(scripts = "/sql/books/clean_books.sql", executionPhase = AFTER_TEST_METHOD)
public class BookControllerDeleteBookTest extends AbstractIntegrationTest {


        @Test
        @WithMockUser
        public void deleteBook() throws Exception {
            BookDetailedDTO deleteBookDTO = BookDetailedDTO.builder()
                    .id(1)
                    .build();
            mockMvc.perform(getMockRequestDeleteBooks(deleteBookDTO))
                    .andExpect(status().is(204));
        }
    }