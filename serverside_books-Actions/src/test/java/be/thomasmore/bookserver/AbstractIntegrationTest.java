package be.thomasmore.bookserver;


import be.thomasmore.bookserver.model.dto.AuthorDetailedDTO;
import be.thomasmore.bookserver.model.dto.BookDetailedDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ContextConfiguration
public abstract class AbstractIntegrationTest {
    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    protected MockMvc mockMvc;

    protected MockHttpServletRequestBuilder getMockRequestGet(String url) {
        return MockMvcRequestBuilders
                .get(url)
                .contentType(MediaType.APPLICATION_JSON);
    }


    protected MockHttpServletRequestBuilder getMockRequestPostBooks(BookDetailedDTO NEW_BOOK_DTO) throws JsonProcessingException {
        return MockMvcRequestBuilders.post("/api/books/")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(NEW_BOOK_DTO));
    }

    protected MockHttpServletRequestBuilder getMockRequestPutAuthors(AuthorDetailedDTO UPDATED_AUTHOR_DTO) throws JsonProcessingException {
        return MockMvcRequestBuilders.put("/api/authors/"+ UPDATED_AUTHOR_DTO.getId())
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(UPDATED_AUTHOR_DTO));
    }

    protected MockHttpServletRequestBuilder getMockRequestPostAuthors(AuthorDetailedDTO NEW_AUTHOR_DTO) throws JsonProcessingException {
        return MockMvcRequestBuilders.post("/api/authors/")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(NEW_AUTHOR_DTO));
    }

    protected MockHttpServletRequestBuilder getMockRequestPutBooks(BookDetailedDTO UPDATED_BOOK_DTO) throws JsonProcessingException {
        return MockMvcRequestBuilders.put("/api/books/"+ UPDATED_BOOK_DTO.getId())
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(UPDATED_BOOK_DTO));
    }

    protected MockHttpServletRequestBuilder getMockRequestDeleteBooks(BookDetailedDTO DELETE_BOOK_DTO) {
        return MockMvcRequestBuilders.delete("/api/books/"+ DELETE_BOOK_DTO.getId())
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

    }
}

