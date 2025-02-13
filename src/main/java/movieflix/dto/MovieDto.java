package movieflix.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MovieDto {

    private Integer movie_id;

    @NotBlank(message = "Please provide movie's title!")
    private String title;

    @NotBlank(message = "Please provide movie's director!")
    private String director;

    @NotBlank(message = "Please provide movie's studio!")
    private String studio;


    private Set<String> movieCast;

    private Integer release_year;

    @NotBlank(message = "Please provide movie's poster!")
    private String poster;

    @NotBlank(message = "Please provide poster's url!")
    private String posterUrl;

}
