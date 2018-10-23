package pemwa.com.javadevsnairobi.service;

import pemwa.com.javadevsnairobi.model.GithubUsersResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubAPI {

    @GET("search/users?q=language:java+location:nairobi")
    Call<GithubUsersResponse> getUsers();

}
