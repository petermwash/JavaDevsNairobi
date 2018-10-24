package pemwa.com.javadevsnairobi.presenter;

import android.util.Log;

import java.util.ArrayList;

import pemwa.com.javadevsnairobi.model.GithubUsers;
import pemwa.com.javadevsnairobi.model.GithubUsersResponse;
import pemwa.com.javadevsnairobi.service.GithubService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class GithubPresenter {

    private GithubService githubService;

    public GithubPresenter() {
        if (this.githubService == null) {
            this.githubService = new GithubService();
        }
    }

    public void getGithubUsers() {
        githubService
                .getGithubAPI()
                .getUsers()
                .enqueue(new Callback<GithubUsersResponse>() {
                    @Override
                    public void onResponse(Call<GithubUsersResponse> call, Response<GithubUsersResponse> response) {
                        if (response.isSuccessful()) {
                            ArrayList<GithubUsers> users = response.body().getUsers();

                            if (users != null) {
                                Log.i(TAG, "onResponse: there is some data received");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GithubUsersResponse> call, Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
