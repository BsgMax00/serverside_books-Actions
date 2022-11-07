package be.thomasmore.bookserver.repositories;

import be.thomasmore.bookserver.model.Author;
import be.thomasmore.bookserver.model.Serie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface SerieRepository extends CrudRepository<Serie, Integer> {
    @NonNull
    List<Serie> findAll();
}


