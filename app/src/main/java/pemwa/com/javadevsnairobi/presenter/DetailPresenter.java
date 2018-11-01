package pemwa.com.javadevsnairobi.presenter;

import pemwa.com.javadevsnairobi.model.UserDetails;
import pemwa.com.javadevsnairobi.service.GithubService;
import pemwa.com.javadevsnairobi.view.DetailPresenterView;
import pemwa.com.javadevsnairobi.view.DetailView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter implements DetailPresenterView {

    private GithubService githubService;
    private DetailView detailView;

    public DetailPresenter(DetailView detailView) {
        this.detailView = detailView;
        if (this.githubService == null) {
            this.githubService = new GithubService();
        }
    }

    @Override
    public void getUserDetails(String userName) {
        githubService
                .getGithubAPI()
                .getUser(userName)
                .enqueue(new Callback<UserDetails>() {
                    @Override
                    public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                        if (response.isSuccessful()) {
                            detailView.displayUserDetail(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserDetails> call, Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
