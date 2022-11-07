package be.thomasmore.bookserver.controllers;

import be.thomasmore.bookserver.model.dto.AuthorDTO;
import be.thomasmore.bookserver.model.dto.AuthorDetailedDTO;
import be.thomasmore.bookserver.services.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/authors")
@Slf4j
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Operation(summary = "find all the authors that are stored in the database ")
    @GetMapping("")
    public Iterable<AuthorDTO> findAll() {
        log.info("##### findAll authors");
        return authorService.findAll();
    }

    @Operation(summary = "get 1 author from the database.",
            description = "Author with id is fetched from database - returns detailed info. ")
    @GetMapping("{id}")
    public AuthorDetailedDTO findOne(@PathVariable int id) {
        log.info(String.format("##### findOne author %d", id));
        return authorService.findOne(id);
    }

    @Operation(summary = "create a new author in the database.",
            description = "This REST call creates a new author in the database")
    @PostMapping("")
    public AuthorDetailedDTO create(@Valid @RequestBody AuthorDetailedDTO authorDto) {
        log.info("##### create author");
        return authorService.create(authorDto);
    }

    @Operation(summary = "edit existing author in the database.",
            description = "Returns updated author. ")
    @PutMapping("{id}")
    public AuthorDetailedDTO edit(@PathVariable int id, @RequestBody AuthorDetailedDTO authorDto) {
        log.info(String.format("##### edit author %d", id));
        return authorService.edit(id, authorDto);
    }
}
