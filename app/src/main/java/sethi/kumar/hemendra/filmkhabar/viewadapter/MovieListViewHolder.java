package sethi.kumar.hemendra.filmkhabar.viewadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import sethi.kumar.hemendra.filmkhabar.R;

/**
 * Created by BAPI1 on 07-02-2016.
 */
public class MovieListViewHolder extends RecyclerView.ViewHolder {
    private ImageView moviePoster;
    private TextView title;
    private TextView hiddenId;

    public MovieListViewHolder(View itemView) {
        super(itemView);
        moviePoster = (ImageView) itemView.findViewById(R.id.movie_poster);
        title = (TextView) itemView.findViewById(R.id.movie_title);
        hiddenId = (TextView) itemView.findViewById(R.id.movieId);
    }

    public ImageView getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(ImageView moviePoster) {
        this.moviePoster = moviePoster;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getHiddenId() {
        return hiddenId;
    }

    public void setHiddenId(TextView hiddenId) {
        this.hiddenId = hiddenId;
    }
}
