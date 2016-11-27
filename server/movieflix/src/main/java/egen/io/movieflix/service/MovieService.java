package egen.io.movieflix.service;

import java.util.List;

import egen.io.movieflix.entity.Movie;

public interface MovieService {

	public Movie findOne(String id);

	public Movie create(Movie movie);

	public Movie update(String movieId, Movie movie);

	public void remove(String movieId);

	public List<Movie> findByType(String movieType, String sortType);

	public List<Movie> findAll();

	public List<Movie> findByGenre(String movieGenre, String sortType);
	
	public List<Movie> findByYear(int movieYear, String sortType);
	
	public List<Movie> findByDirector(String movieDirector);
	
	public List<Movie> findByimdbId(String movieimdbId);

	public List<Movie> paginatedList(int start,int size);
	
}
