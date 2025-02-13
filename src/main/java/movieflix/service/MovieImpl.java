package movieflix.service;

import movieflix.dto.MovieDto;
import movieflix.entities.Movie;
import movieflix.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class MovieImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final FileService fileService;
    public MovieImpl(MovieRepository movieRepository, FileService fileService) {
        this.movieRepository = movieRepository;
        this.fileService = fileService;
    }
    @Value("${project.poster}")
    private String path;
    @Value("${base.url}")
    private String baseUrl;

    @Override
    public MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException {
        //upload the file
        String uploadedFileName=fileService.uploadFile(path,file);
        //set the value of the field 'poster' as fileName
        movieDto.setPoster(uploadedFileName);

        // 3. map dto to Movie object
        Movie movie = new Movie(
                null,
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getStudio(),
                movieDto.getMovieCast(),
                movieDto.getRelease_year(),
                movieDto.getPoster()
        );
        //save the movie object ->saved Movie Object
        Movie savedMovie=movieRepository.save(movie);
        //generate poster URL
        String postUrl=baseUrl+"/file/"+uploadedFileName;

        //map movie object to Dto object return it
        MovieDto response=new MovieDto(
                savedMovie.getMovie_id(),
                savedMovie.getTitle(),
                savedMovie.getDirector(),
                savedMovie.getStudio(),
                savedMovie.getMovieCast(),
                savedMovie.getRelease_year(),
                savedMovie.getPoster(),
                postUrl
        );

        return response;
    }

    @Override
    public MovieDto getMovie(Integer movieId) {
        //check the data in DB and if exists, fetch the data of given ID
        Movie movie=movieRepository.findById(movieId).orElseThrow(()->new RuntimeException("movie not Found!!"));

        //generate posterUrl
        String postUrl=baseUrl+"/file/"+movie.getPoster();
        
        //map to MovieDto object and return it
        MovieDto response=new MovieDto(
                movie.getMovie_id(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getRelease_year(),
                movie.getPoster(),
                postUrl
        );
        return response;
    }

    @Override
    public List<MovieDto> getAllMovie() {
        //1. fetch all data from DB
        List<Movie> movies=movieRepository.findAll();
        List<MovieDto> movieDtos=new ArrayList<>();
        //2. iterate through the list, generate posterUrl for each movie Obj, and map to the MovieDto Obj
        for(Movie movie:movies){
            String postUrl=baseUrl+"/file/"+movie.getPoster();
            MovieDto movieDto=new MovieDto(
                    movie.getMovie_id(),
                    movie.getTitle(),
                    movie.getDirector(),
                    movie.getStudio(),
                    movie.getMovieCast(),
                    movie.getRelease_year(),
                    movie.getPoster(),
                    postUrl
            );
            movieDtos.add(movieDto);
        }
        return movieDtos;
    }
}
