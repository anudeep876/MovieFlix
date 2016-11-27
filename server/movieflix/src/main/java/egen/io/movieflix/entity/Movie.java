package egen.io.movieflix.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "Movie.findAll", query = "SELECT m from Movie m"),
    @NamedQuery(name = "Movie.findByName", query = "SELECT m from Movie m where m.Title=:ptitle"),
    @NamedQuery(name = "Movie.findByType", query = "SELECT m from Movie m where m.Type=:ptype ORDER BY m.Type DESC"),
    @NamedQuery(name = "Movie.findByGenre", query = "SELECT m from Movie m where m.Genre like :pgenre ORDER BY m.Genre DESC"),
    @NamedQuery(name = "Movie.findByYear", query = "SELECT m from Movie m where m.Year=:pyear ORDER BY m.Year DESC"),
    @NamedQuery(name = "Movie.findByDirector", query = "SELECT m from Movie m where m.Director like :pdirector"),
    @NamedQuery(name = "Movie.findByimdbId", query = "SELECT m from Movie m where m.imdbId=:pimdbId"),
    @NamedQuery(name = "Movie.findByTypeSortByYear", query = "SELECT m from Movie m where m.Type=:ptype ORDER BY m.Year DESC"),
    @NamedQuery(name = "Movie.findByTypeSortByimdbRating", query = "SELECT m from Movie m where m.Type=:ptype ORDER BY m.imdbRating DESC"),
    @NamedQuery(name = "Movie.findByTypeSortByimdbVotes", query = "SELECT m from Movie m where m.Type=:ptype ORDER BY m.imdbVotes DESC"),
    @NamedQuery(name = "Movie.findByYearSortByimdbRating", query = "SELECT m from Movie m where m.Year=:pyear ORDER BY m.imdbRating DESC"),
    @NamedQuery(name = "Movie.findByYearSortByimdbVotes", query = "SELECT m from Movie m where m.Year=:pyear ORDER BY m.imdbVotes DESC"),
    @NamedQuery(name = "Movie.findByGenreSortByYear", query = "SELECT m from Movie m where m.Genre like :pgenre ORDER BY m.Year DESC"),
    @NamedQuery(name = "Movie.findByGenreSortByimdbRating", query = "SELECT m from Movie m where m.Genre like :pgenre ORDER BY m.imdbRating DESC"),
    @NamedQuery(name = "Movie.findByGenreSortByimdbVotes", query = "SELECT m from Movie m where m.Genre like :pgenre ORDER BY m.imdbVotes DESC"),})
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVIE_ID")
    private int id;

    @Column(unique = true, nullable = false)
    @JsonProperty("Title")
    private String Title;

    @Column(nullable = false)
    @JsonProperty("Year")
    private int Year;

    @JsonProperty("Rated")
    private String Rated;

    @Column(nullable = false)
    @JsonProperty("Released")
    private String Released;

    @Column(nullable = false)
    @JsonProperty("Runtime")
    private String Runtime;

    @Column(nullable = false)
    @JsonProperty("Genre")
    private String Genre;

    @Column(nullable = false)
    @JsonProperty("Director")
    private String Director;

    @Column(nullable = false)
    @JsonProperty("Writer")
    private String Writer;

    @Column(nullable = false)
    @JsonProperty("Actors")
    private String Actors;

    @Column(nullable = false)
    @JsonProperty("Plot")
    private String Plot;

    @Column(nullable = false)
    @JsonProperty("Language")
    private String Language;

    @Column(nullable = false)
    @JsonProperty("Country")
    private String Country;

    @JsonProperty("Awards")
    private String Awards;

    @Column(nullable = false, unique = true)
    @JsonProperty("Poster")
    private String Poster;

    @JsonProperty("Metascore")
    private int Metascore;

    @JsonProperty("imdbRating")
    private double imdbRating;

    @JsonProperty("imdbVotes")
    private long imdbVotes;

    @Column(unique = true)
    private String imdbId;

    @Column(nullable = false)
    @JsonProperty("Type")
    private String Type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getRated() {
        return Rated;
    }

    public void setRated(String rated) {
        Rated = rated;
    }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String released) {
        Released = released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getAwards() {
        return Awards;
    }

    public void setAwards(String awards) {
        Awards = awards;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public int getMetascore() {
        return Metascore;
    }

    public void setMetascore(int metascore) {
        Metascore = metascore;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public long getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(long imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbID() {
        return imdbId;
    }

    public void setImdbID(String imdbID) {
        this.imdbId = imdbID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @ManyToMany
    private List<User> users;

    public Movie() {
//        id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", Title=" + Title + ", Year=" + Year + ", Rated=" + Rated + ", Released=" + Released
                + ", Runtime=" + Runtime + ", Genre=" + Genre + ", Director=" + Director + ", Writer=" + Writer
                + ", Actors=" + Actors + ", Plot=" + Plot + ", Language=" + Language + ", Country=" + Country
                + ", Awards=" + Awards + ", Poster=" + Poster + ", Metascore=" + Metascore + ", imdbRating="
                + imdbRating + ", imdbVotes=" + imdbVotes + ", imdbId=" + imdbId + ", Type=" + Type + ", users=" + users
                + "]";
    }

}
