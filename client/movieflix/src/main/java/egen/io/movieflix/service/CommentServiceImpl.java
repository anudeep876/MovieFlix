package egen.io.movieflix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egen.io.movieflix.entity.Comment;
import egen.io.movieflix.exception.CommentAlreadyExistException;
import egen.io.movieflix.exception.CommentNotFoundException;
import egen.io.movieflix.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Transactional
    @Override
    public Comment create(Comment comment) {
//        Comment existing = commentRepository.create(comment);
        return commentRepository.create(comment);
    }

    @Override
    public Comment findOne(String commentId) {
        Comment comment = commentRepository.findOne(commentId);
        if (comment == null) {
            throw new CommentNotFoundException("Comment not found");
        }
        return comment;
    }

    @Transactional
    @Override
    public Comment update(String commentId, Comment comment) {
        Comment existing = commentRepository.findOne(commentId);
        if (existing == null) {
            throw new CommentNotFoundException("Comment not found");
        }
        return commentRepository.update(comment);
    }

    @Transactional
    @Override
    public void remove(String commentId) {
        Comment existing = commentRepository.findOne(commentId);
        if (existing == null) {
            throw new CommentNotFoundException("Comment not found");
        }
        commentRepository.delete(existing);
    }

    @Override
    public List<Comment> findByMovie(String movieId, String userId) {
        System.out.println("Service");
        if (commentRepository.findByMovie(movieId, userId) == null) {
            throw new CommentNotFoundException("Comment not found");
        }
        return commentRepository.findByMovie(movieId, userId);
    }
    
    @Override
    public List<Comment> findOtherUsersMovieComments(String movieId, String userId) {
        System.out.println("Service");
        if (commentRepository.findOtherUsersMovieComments(movieId, userId) == null) {
            throw new CommentNotFoundException("Comment not found");
        }
        return commentRepository.findOtherUsersMovieComments(movieId, userId);
    }

}
