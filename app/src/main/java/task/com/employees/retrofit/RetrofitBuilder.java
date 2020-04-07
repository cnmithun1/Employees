package task.com.employees.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private String apiUrl = "http://dummy.restapiexample.com";

    public Retrofit.Builder apiBuilder;

    public Retrofit api;

    public ApiClient apiClient;


    public RetrofitBuilder(){

        apiBuilder = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create());
                //.client(getUnsafeOkHttpClient().retryOnConnectionFailure(true).build());
        api = apiBuilder.build();
        apiClient = api.create(ApiClient.class);

    }
}
