package sethi.kumar.hemendra.filmkhabar.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by BAPI1 on 06-02-2016.
 */
public class ServiceFactory {
    public static <T> T createRetrofitService(final Class<T> clazz, final String endpoint){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .build();

        T service = retrofit.create(clazz);
        return service;
    }
}
