package egen.io.movieflix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egen.io.movieflix.entity.Rating;
import egen.io.movieflix.exception.RatingNotFoundException;
import egen.io.movieflix.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating findOne(String ratingId) {
        Rating rating = ratingRepository.findOne(ratingId);
        if (rating == null) {
            throw new RatingNotFoundException("Rating not found");
        }
        return rating;
    }

    @Transactional
    @Override
    public Rating create(Rating rating) {
        return ratingRepository.create(rating);
    }

    @Transactional
    @Override
    public Rating update(String ratingId, Rating rating) {
        Rating existing = ratingRepository.findOne(ratingId);
        if (existing == null) {
            throw new RatingNotFoundException("Rating not found");
        }
        return ratingRepository.update(rating);
    }

    @Transactional
    @Override
    public void remove(String ratingId) {
        Rating existing = ratingRepository.findOne(ratingId);
        if (existing == null) {
            throw new RatingNotFoundException("Rating not found");
        }
        ratingRepository.delete(existing);
    }

    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public double averatgeRating(String movieId) {
        return ratingRepository.averatgeRating(movieId);
    }

}
