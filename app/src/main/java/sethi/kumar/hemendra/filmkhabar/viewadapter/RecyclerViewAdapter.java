package sethi.kumar.hemendra.filmkhabar.viewadapter;

import android.content.Context;
import android.graphics.Movie;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import sethi.kumar.hemendra.filmkhabar.R;
import sethi.kumar.hemendra.filmkhabar.model.Movies;
import sethi.kumar.hemendra.filmkhabar.utility.MovieUtility;

/**
 * Created by BAPI1 on 07-02-2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Movies> movieList;
    private LayoutInflater inflater = null;
    private Uri uri = null;
    private final String TAG = RecyclerViewAdapter.class.getSimpleName();

    public RecyclerViewAdapter(Context context, List<Movies> movieList) {
        this.context = context;
        this.movieList = movieList;
        inflater = LayoutInflater.from(context);
    }

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View view = inflater.inflate(R.layout.movie_list_layout, null);
        viewHolder = new MovieListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        MovieListViewHolder movieListViewHolder = (MovieListViewHolder) holder;
        Movies movies = (Movies) movieList.get(position);
        movieListViewHolder.getTitle().setText(movies.getOriginal_title().toString());
        movieListViewHolder.getHiddenId().setText(movies.getId().toString());
        uri = Uri.parse(MovieUtility.IMG_URL+movies.getPoster_path());

        Log.d(TAG, "onBindViewHolder: URI : " + uri.toString());

        Picasso.with(context)
                .load(uri)
                .into(movieListViewHolder.getMoviePoster());
    }

    @Override
    public int getItemCount() {
        int v = movieList== null ? 0:movieList.size();
        Log.d(this.getClass().getSimpleName(), "getItemCount: Size" + v);
        return movieList== null ? 0:movieList.size();
    }

    public void updateList(List<Movies> movieUpdateList){
        if (movieList==null){
            movieList = movieUpdateList;
        }
        notifyDataSetChanged();
    }
}
