package pemwa.com.javadevsnairobi.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubService {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.github.com/";


    public static GithubAPI getGithubAPI() {
        Gson gson = new GsonBuilder().setLenient().create();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit.create(GithubAPI.class);
    }
}
