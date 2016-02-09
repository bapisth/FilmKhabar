package sethi.kumar.hemendra.filmkhabar.service;


import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import sethi.kumar.hemendra.filmkhabar.model.Credits;
import sethi.kumar.hemendra.filmkhabar.model.Movies;
import sethi.kumar.hemendra.filmkhabar.model.TopRatedMovies;
import sethi.kumar.hemendra.filmkhabar.model.UpcomingMovies;

/**
 * Created by BAPI1 on 05-02-2016.
 */
public interface TMDBService {
    public final String API_KEY = "api_key=826d2b2f6a23b79f4ad5b1bdf73594f3";
    public final String SERVICE_END = "https://api.themoviedb.org/3/movie/";

    @GET("{movieId}?"+API_KEY)
    Observable<Movies> getMovies(@Path("movieId") int movieId);

    @GET("upcoming?"+API_KEY)
    Observable<UpcomingMovies> getUpComingMovies(@Query("page") int pageNum);

    @GET("top_rated?"+API_KEY)
    Observable<TopRatedMovies> getTopRatedMovies(@Query("page") int pageNum);

    @GET("latest?"+API_KEY)
    Observable<TopRatedMovies> getLatestMovies(@Query("page") int pageNum);

    @GET("{movieId}/credits")
    Observable<Credits> getCredits(@Path("movieId") int movieId);
}
