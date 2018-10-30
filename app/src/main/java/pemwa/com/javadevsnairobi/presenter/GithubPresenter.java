package pemwa.com.javadevsnairobi.presenter;

import android.util.Log;

import java.util.ArrayList;

import pemwa.com.javadevsnairobi.model.GithubUsers;
import pemwa.com.javadevsnairobi.model.GithubUsersResponse;
import pemwa.com.javadevsnairobi.service.GithubService;
import pemwa.com.javadevsnairobi.view.UsersPresenterView;
import pemwa.com.javadevsnairobi.view.UsersView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class GithubPresenter implements UsersPresenterView {

    private GithubService githubService;
    private UsersView usersView;

    public GithubPresenter(UsersView usersView) {
        this.usersView = usersView;
        if (this.githubService == null) {
            this.githubService = new GithubService();
        }
    }

    @Override
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
                                usersView.displayGithubUsers(users);
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
