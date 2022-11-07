package be.thomasmore.bookserver.model.converters;

import be.thomasmore.bookserver.model.Author;
import be.thomasmore.bookserver.model.Serie;
import be.thomasmore.bookserver.model.dto.AuthorDetailedDTO;
import be.thomasmore.bookserver.model.dto.SerieDTO;
import be.thomasmore.bookserver.model.dto.SerieDetailedDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SerieDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param serie the entity from the db
     * @return SerieDTO object to send to the client.
     */
    public SerieDTO convertToDto(Serie serie) {
        return modelMapper.map(serie, SerieDTO.class);
    }
}
