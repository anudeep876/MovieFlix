package egen.io.movieflix.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import egen.io.movieflix.entity.Movie;
import javax.persistence.Query;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Movie> findAll() {
        TypedQuery<Movie> query = em.createNamedQuery("Movie.findAll", Movie.class);
        return query.getResultList();
    }

    @Override
    public List<Movie> paginatedList(int start, int size) {
        TypedQuery<Movie> query = em.createNamedQuery("Movie.findAll", Movie.class);
        List<Movie> list = query.getResultList();
        if (start + size > list.size()) {
            return new ArrayList<>();
        }
        return list.subList(start, start + size);
    }

    @Override
    public Movie findOne(String movieId) {
        System.out.println(movieId);
        return em.find(Movie.class, Integer.parseInt(movieId));
    }

    @Override
    public Movie findByName(String title) {
        TypedQuery<Movie> query = em.createNamedQuery("Movie.findByName", Movie.class);
        query.setParameter("ptitle", title);
        List<Movie> movies = query.getResultList();
        if (movies.size() == 1) {
            return movies.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Movie create(Movie movie) {
        em.persist(movie);
        return movie;
    }

    @Override
    public Movie update(Movie movie) {
        em.merge(movie);
//        em.flush();
        return movie;
    }

    @Override
    public void delete(Movie existing) {
        em.remove(existing);
    }

    @Override
    public List<Movie> findByType(String movieType, String sortType) {
        TypedQuery<Movie> querySort = null;
        if (sortType.equals("year")) {
            querySort = em.createNamedQuery("Movie.findByTypeSortByYear", Movie.class);
        } else if (sortType.equals("rating")) {
            querySort = em.createNamedQuery("Movie.findByTypeSortByimdbRating", Movie.class);
        } else if (sortType.equals("votes")) {
            querySort = em.createNamedQuery("Movie.findByTypeSortByimdbVotes", Movie.class);
        } else if (sortType.equals("name")) {
            movieType = "%" + movieType + "%";
            querySort = em.createNamedQuery("Movie.searchName", Movie.class);
        } else {
            querySort = em.createNamedQuery("Movie.findByType", Movie.class);
        }

        querySort.setParameter("ptype", movieType);
        List<Movie> movies = querySort.getResultList();
        return movies;

    }

    @Override
    public List<Movie> sortBySelection(String selectedCol) {
        TypedQuery<Movie> querySort = null;
        if (selectedCol.equals("rating")) {
            querySort = em.createNamedQuery("Movie.sortByRating", Movie.class);
        } else if (selectedCol.equals("year")) {
            querySort = em.createNamedQuery("Movie.sortByYear", Movie.class);
        } else if (selectedCol.equals("votes")) {
            querySort = em.createNamedQuery("Movie.sortByVotes", Movie.class);
        } else {
            querySort = em.createNamedQuery("Movie.findAll", Movie.class);
        }

        List<Movie> movies = querySort.getResultList();
        return movies;

    }

    @Override
    public List<Movie> findByGenre(String movieGenre, String sortType) {
        TypedQuery<Movie> querySort = null;
        if (sortType.equals("year")) {
            querySort = em.createNamedQuery("Movie.findByGenreSortByYear", Movie.class);
        } else if (sortType.equals("rating")) {
            querySort = em.createNamedQuery("Movie.findByGenreSortByimdbRating", Movie.class);
        } else if (sortType.equals("votes")) {
            querySort = em.createNamedQuery("Movie.findByGenreSortByimdbVotes", Movie.class);
        } else {
            querySort = em.createNamedQuery("Movie.findByGenre", Movie.class);
        }

        querySort.setParameter("pgenre", "%" + movieGenre + "%");
        List<Movie> movies = querySort.getResultList();
        return movies;

    }

    @Override
    public List<Movie> findByYear(int movieYear, String sortType) {
        TypedQuery<Movie> querySort = null;
        if (sortType.equals("rating")) {
            querySort = em.createNamedQuery("Movie.findByYearSortByimdbRating", Movie.class);
        } else if (sortType.equals("votes")) {
            querySort = em.createNamedQuery("Movie.findByYearSortByimdbVotes", Movie.class);
        } else {
            querySort = em.createNamedQuery("Movie.findByYear", Movie.class);
        }

        querySort.setParameter("pyear", movieYear);
        List<Movie> movies = querySort.getResultList();
        return movies;
    }

    @Override
    public List<Movie> findByDirector(String movieDirector) {
        TypedQuery<Movie> query = em.createNamedQuery("Movie.findByDirector", Movie.class);
        query.setParameter("pdirector", movieDirector);
        return query.getResultList();
    }

    @Override
    public List<Movie> findByimdbId(String movieimdbId) {
        TypedQuery<Movie> query = em.createNamedQuery("Movie.findByimdbId", Movie.class);
        query.setParameter("pimdbId", movieimdbId);
        return query.getResultList();
    }

    @Override
    public List<String> getMoviePosters(int posterCount) {
        List<String> postersList = new ArrayList();
        TypedQuery<Movie> query = em.createNamedQuery("Movie.findAll", Movie.class);
        List<Movie> movies = query.getResultList();
        for (int poster = 0; poster < movies.size() && poster < posterCount; poster++) {
            postersList.add(movies.get(poster).getPoster());
        }
        return postersList;
    }
}
