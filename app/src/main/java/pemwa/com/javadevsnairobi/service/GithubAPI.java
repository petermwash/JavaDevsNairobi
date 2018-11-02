package pemwa.com.javadevsnairobi.service;

import pemwa.com.javadevsnairobi.model.GithubUsersResponse;
import pemwa.com.javadevsnairobi.model.UserDetails;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubAPI {

    @GET("search/users?q=language:java+location:nairobi")
    Call<GithubUsersResponse> getUsers();

    @GET("users/{user}")
    Call<UserDetails> getUser(@Path("user") String user);

}
