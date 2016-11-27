package egen.io.movieflix.service;

import java.util.List;

import egen.io.movieflix.entity.Rating;

public interface RatingService {

    public Rating findOne(String ratingId);

    public Rating update(String ratingId, Rating rating);

    public void remove(String ratingId);

    public List<Rating> findAll();

    public Rating create(Rating rating);

    public double averatgeRating(String movieId);
}
