package egen.io.movieflix.repository;

import java.util.List;
import java.util.Map;

import egen.io.movieflix.entity.Movie;

public interface MovieRepository {

    public List<Movie> findAll();

    public Movie findOne(String movieId);

    public Movie create(Movie movie);

    public Movie update(Movie movie);

    public void delete(Movie existing);

    public Movie findByName(String movieName);

    public List<Movie> findByType(String movieType, String sortType);

    public List<Movie> findByGenre(String movieGenre, String sortType);

    public List<Movie> findByYear(int movieYear, String sortType);

    public List<Movie> findByDirector(String movieDirector);

    public List<Movie> findByimdbId(String movieimdbId);

    public List<Movie> paginatedList(int start, int size);

    public List<Movie> sortBySelection(String selectedCol);

    public List<String> getMoviePosters(int posterCount);
}
