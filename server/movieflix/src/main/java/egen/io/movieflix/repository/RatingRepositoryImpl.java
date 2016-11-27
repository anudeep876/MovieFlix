package egen.io.movieflix.repository;

import egen.io.movieflix.entity.Movie;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import egen.io.movieflix.entity.Rating;
import egen.io.movieflix.entity.User;
import egen.io.movieflix.exception.RatingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class RatingRepositoryImpl implements RatingRepository {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired(required = false)
    private Movie movie;

    @Autowired(required = false)
    private User user;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Rating> findAll() {
        TypedQuery<Rating> query = em.createNamedQuery("Rating.findAll", Rating.class);
        return query.getResultList();
    }

    @Override
    public Rating findOne(String ratingId) {
        return em.find(Rating.class, Integer.parseInt(ratingId));
    }

    @Override
    public Rating create(Rating rating) {
        User user = em.find(User.class, rating.getUser().getId());
        Movie movie = em.find(Movie.class, rating.getMovie().getId());
        rating.setUser(user);
        rating.setMovie(movie);
        em.persist(rating);
        return rating;
    }

    @Override
    public Rating update(Rating rating) {
        return em.merge(rating);
    }

    @Override
    public void delete(Rating existing) {
        em.remove(existing);
    }

    @Override
    public Rating findByRanking(int movieRanking) {
        TypedQuery<Rating> query = em.createNamedQuery("Rating.findByRanking", Rating.class);
        query.setParameter("pmovieRanking", movieRanking);
        List<Rating> ratings = query.getResultList();
        if (ratings.size() == 1) {
            return ratings.get(0);
        } else {
            return null;
        }
    }

    @Override
    public double averatgeRating(String movieId) {
        Movie movie = em.find(Movie.class, Integer.parseInt(movieId));
        if (movie == null) {
            throw new RatingNotFoundException("Movie not found");
        }
//        System.out.println((Double) em.createNamedQuery("Rating.findAvgRating").setParameter("pmovieId", movie).getSingleResult());
        return (Double) em.createNamedQuery("Rating.findAvgRating").setParameter("pmovieId", movie).getSingleResult();
//        return 0;
    }

}
