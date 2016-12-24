package egen.io.movieflix.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "Rating.findAll", query = "SELECT r from Rating r"),
    @NamedQuery(name = "Rating.findAvgRating", query = "SELECT avg(r.userRating) from Rating r where r.movie=:pmovieId")
})
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RATING_ID")
    private int id;
    private double userRating;
//    @Transient
//    private double averageRating;
//
//    public double getAverageRating() {
//        return averageRating;
//    }
//
//    public void setAverageRating(double averageRating) {
//        this.averageRating = averageRating;
//    }

    public Rating() {
//        id = UUID.randomUUID().toString();
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public int getId() {
        return id;
    }

    @ManyToOne(cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @JoinColumn(name = "USER_ID")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn(name = "MOVIE_ID", referencedColumnName = "MOVIE_ID")
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setId(int id) {
        this.id = id;
    }

}
