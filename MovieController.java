/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.week03;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Adhi Baskoro 29401887
 */
@Named(value = "movieController")
@RequestScoped
public class MovieController {

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    
    private String pageTitle;
    private int movieId;
    private Movie movie;
    
    public MovieController() {
        //Asign movie identifier via GET parameter.
        movieId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("movieID"));
        //Assign movie based on the id provided
        movie = getMovie();
        //Then we set the page title to have the movie title added
        pageTitle = movie.getTitle() + "|" + MovieApplication.APP_TITLE;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public int getMovieId() {
        return movieId;
    }
    
    public String setPageTitle() {
        return pageTitle;
    }
    
    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }
    
    private Movie getMovie() {
        if (movie == null) {
            //Get application context bean MovieApplication
            ELContext context
                    = FacesContext.getCurrentInstance().getELContext();
            MovieApplication app
                    = (MovieApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "movieApplication");
            //-1 to movieId since we +1 in JSF (to always have positive movie id)
            return app.getMovies().get(--movieId);
        }
        return movie;
    }
    
}
