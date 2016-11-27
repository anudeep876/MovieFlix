package egen.io.movieflix.service;

import java.util.List;

import egen.io.movieflix.entity.Comment;

public interface CommentService {

    public List<Comment> findAll();

    public Comment findOne(String commentId);

    public void remove(String commentId);

    public Comment update(String commentId, Comment comment);

    public Comment create(Comment comment);

    public List<Comment> findByMovie(String movieId, String userId);

    public List<Comment> findOtherUsersMovieComments(String movieId, String userId);

}
