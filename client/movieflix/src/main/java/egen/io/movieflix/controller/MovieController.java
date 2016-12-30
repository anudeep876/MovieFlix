package egen.io.movieflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egen.io.movieflix.entity.Movie;
import egen.io.movieflix.service.MovieService;

@RestController
@RequestMapping(value = "movies", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Movie> findAll() {
        return movieService.findAll();
    }

    @RequestMapping(value = "/paginatedList", method = RequestMethod.GET, params = "start")
    public List<Movie> paginatedList(@RequestParam("start") int start, @RequestParam(value = "size") int size) {
        return movieService.paginatedList(start, size);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public Movie findOne(@PathVariable("id") String movieId) {
        return movieService.findOne(movieId);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Movie create(@RequestBody Movie movie) {
        return movieService.create(movie);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Movie update(@PathVariable("id") String movieId, @RequestBody Movie movie) {
        return movieService.update(movieId, movie);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable("id") String movieId) {
        movieService.remove(movieId);
    }

    @RequestMapping(method = RequestMethod.GET, params = "type")
    public List<Movie> findByType(@RequestParam("type") String movieType, @RequestParam(required = false, value = "sort") String typeSort) {
        return movieService.findByType(movieType, typeSort);
    }

    @RequestMapping(value = "/sortByCol", method = RequestMethod.GET)
    public List<Movie> sortBySelection(@RequestParam("sortCol") String sortCol) {
        return movieService.sortBySelection(sortCol);
    }

    @RequestMapping(method = RequestMethod.GET, params = "genre")
    public List<Movie> findByGenre(@RequestParam("genre") String movieGenre, @RequestParam(required = false, value = "sort") String typeSort) {
        return movieService.findByGenre(movieGenre, typeSort);
    }

    @RequestMapping(method = RequestMethod.GET, params = "director")
    public List<Movie> findByDirector(@RequestParam("director") String movieDirector) {
        return movieService.findByDirector(movieDirector);
    }

    @RequestMapping(method = RequestMethod.GET, params = "imdbId")
    public List<Movie> findByimdbId(@RequestParam("imdbId") String movieimdbId) {
        return movieService.findByimdbId(movieimdbId);
    }

    @RequestMapping(method = RequestMethod.GET, params = "year")
    public List<Movie> findByYear(@RequestParam("year") int movieYear, @RequestParam(required = false, value = "sort") String typeSort) {
        return movieService.findByYear(movieYear, typeSort);
    }

    @RequestMapping(value = "/getMoviePosters", method = RequestMethod.GET)
    public List<String> getMoviePosters(@RequestParam(required = false, value = "movieCount") int movieCount) {
        if (movieCount == 0) {
            movieCount = 100;
        }
        return movieService.getMoviePosters(movieCount);
    }
}
