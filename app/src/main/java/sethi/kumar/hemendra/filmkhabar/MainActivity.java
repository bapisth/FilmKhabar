package sethi.kumar.hemendra.filmkhabar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.Random;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sethi.kumar.hemendra.filmkhabar.activities.CreditDetailActivity;
import sethi.kumar.hemendra.filmkhabar.listener.RecyclerItemClickListener;
import sethi.kumar.hemendra.filmkhabar.model.Movies;
import sethi.kumar.hemendra.filmkhabar.model.UpcomingMovies;
import sethi.kumar.hemendra.filmkhabar.service.ServiceFactory;
import sethi.kumar.hemendra.filmkhabar.service.TMDBService;
import sethi.kumar.hemendra.filmkhabar.viewadapter.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView movieRecylerView;
    private static List<Movies> moviesList;
    private LinearLayoutManager layoutManager;

   RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRecylerView = (RecyclerView) findViewById(R.id.movie_recycler_view);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this);
        movieRecylerView.setLayoutManager(layoutManager);
        movieRecylerView.setAdapter(recyclerViewAdapter);

        TMDBService tmdbService = ServiceFactory.createRetrofitService(TMDBService.class, TMDBService.SERVICE_END);
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Downloading...");
        dialog.show();

        int pageNum = new Random().nextInt(5);
        pageNum = pageNum==0?1:pageNum;
        Toast.makeText(MainActivity.this, "Requesting Page Number :" + pageNum, Toast.LENGTH_SHORT).show();
        Observable<UpcomingMovies> upcomingMoviesObservable = tmdbService.getUpComingMovies(pageNum);
        upcomingMoviesObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpcomingMovies>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: Upcoming movies operation download completer....." + moviesList.size());
                        dialog.hide();
                        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, moviesList);
                        movieRecylerView.setLayoutManager(layoutManager);
                        movieRecylerView.setAdapter(recyclerViewAdapter);
                        movieRecylerView.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity.this, new RecyclerItemClickListener.OnListItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Toast.makeText(MainActivity.this, "Item Clicked", Toast.LENGTH_SHORT).show();
                                TextView movieId = (TextView) view.findViewById(R.id.movieId);
                                int id = Integer.parseInt(movieId.getText().toString());
                                Toast.makeText(MainActivity.this, "Item Clicked Movie Id="+id, Toast.LENGTH_SHORT).show();
                                Intent creditDetailIntent = new Intent(MainActivity.this, CreditDetailActivity.class);
                                creditDetailIntent.putExtra("movieId", id);
                                startActivity(creditDetailIntent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        },movieRecylerView ));
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        dialog.hide();
                        Toast.makeText(MainActivity.this, "Having Trouble fetching data :" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                        Log.d(TAG, "onError: Getting Error while fetching upComing Movies............");
                    }

                    @Override
                    public void onNext(UpcomingMovies upcomingMovies) {
                        Log.d(TAG, "onNext: Yes Getting upComing Movies list........=>");
                        if (upcomingMovies.getResults() != null) {
                            Log.d(TAG, "onNext: Yes Getting upComing Movies list.......VITARE.=>");
                            MainActivity.moviesList = upcomingMovies.getResults();
                        }
                    }
                });
    }

    private void populateRecycler(List<Movies> moviesList) {
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, moviesList);
        Log.d(TAG, "populateRecycler: Update Notify change");
        recyclerViewAdapter. notifyDataSetChanged();
    }
}
