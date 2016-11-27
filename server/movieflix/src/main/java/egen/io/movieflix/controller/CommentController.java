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

import egen.io.movieflix.entity.Comment;
import egen.io.movieflix.service.CommentService;

@RestController
@RequestMapping(value = "comments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Comment> findAll() {
        return commentService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Comment create(@RequestBody Comment comment) {
        return commentService.create(comment);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public Comment findOne(@PathVariable("id") String commentId) {
        return commentService.findOne(commentId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Comment update(@PathVariable("id") String commentId, @RequestBody Comment comment) {
        return commentService.update(commentId, comment);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable("id") String commentId) {
        commentService.remove(commentId);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"movie", "user"})
    public List<Comment> findByMovie(@RequestParam("movie") String movieId, @RequestParam("user") String userId) {
        return commentService.findByMovie(movieId, userId);
    }
    
    @RequestMapping(method = RequestMethod.POST, params = {"movie", "user"})
    public List<Comment> findOtherUsersMovieComments(@RequestParam("movie") String movieId, @RequestParam("user") String userId) {
        return commentService.findOtherUsersMovieComments(movieId, userId);
    }
}
