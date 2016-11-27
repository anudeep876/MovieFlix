package egen.io.movieflix.repository;

import java.util.List;

import egen.io.movieflix.entity.Comment;

public interface CommentRepository {

    public List<Comment> findAll();

    public Comment findOne(String commentId);

    public void delete(Comment existing);

    public Comment update(Comment comment);

    public Comment create(Comment comment);

    public List<Comment> findByMovie(String movieId, String userId);

    public List<Comment> findOtherUsersMovieComments(String movieId, String userId);

}
