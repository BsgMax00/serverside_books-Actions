package be.thomasmore.bookserver.controllers;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import be.thomasmore.bookserver.model.Author;
import be.thomasmore.bookserver.model.dto.AuthorDetailedDTO;
import be.thomasmore.bookserver.repositories.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/authors/create_2_authors.sql")
@Sql(scripts = "/sql/authors/clean_authors.sql", executionPhase = AFTER_TEST_METHOD)
public class AuthorControllerUpdateAuthorTest extends AbstractIntegrationTest {


    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @WithMockUser()
    public void updateAuthor() throws Exception {
        final String UPDATED_AUTHOR_NAME = "It is simple to update a author";
        AuthorDetailedDTO updatedAuthorDto = AuthorDetailedDTO.builder()
                .name(UPDATED_AUTHOR_NAME)
                .id(1)
                .build();

        mockMvc.perform(getMockRequestPutAuthors(updatedAuthorDto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value(UPDATED_AUTHOR_NAME));

        Author loadedAuthor = authorRepository.findByName(UPDATED_AUTHOR_NAME).orElseThrow();
        assertThat(loadedAuthor.getName()).isEqualTo(UPDATED_AUTHOR_NAME);
    }

}
