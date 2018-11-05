package pemwa.com.javadevsnairobi.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import pemwa.com.javadevsnairobi.R;
import pemwa.com.javadevsnairobi.model.UserDetails;
import pemwa.com.javadevsnairobi.presenter.DetailPresenter;

public class DetailActivity extends AppCompatActivity implements DetailView {

    ImageView profileImage;
    ImageView share;
    TextView username;
    TextView company;
    TextView url;
    TextView followers;
    TextView repo;
    TextView location;

    DetailPresenterView detailPresenterView;
    UserDetails userDetails;
    String userName;
    String profPic;
    String urlData;

    ProgressDialog progressDialog;

    final String USER_KEY = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_detail);

        progressDialog = new ProgressDialog(DetailActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        detailPresenterView = new DetailPresenter(this);

        profileImage = findViewById(R.id.userProfile);
        username = findViewById(R.id.userName);
        url = findViewById(R.id.userGithubLink);
        repo = findViewById(R.id.userRepos);
        followers = findViewById(R.id.userFollowers);
        location = findViewById(R.id.userLocation);
        company = findViewById(R.id.userOrganisation);


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        profPic = bundle.getString("profPic");
        userName = bundle.getString("userName");
        urlData = bundle.getString("url");

        if (savedInstanceState != null) {
            userDetails = savedInstanceState.getParcelable(USER_KEY);
            this.displayUserDetail(userDetails);
        }else {
            detailPresenterView.getUserDetails(userName);
        }

        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userProfile = new Intent(Intent.ACTION_VIEW, Uri.parse(urlData));
                startActivity(userProfile);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putParcelable(USER_KEY, userDetails);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void displayUserDetail(UserDetails user) {

        progressDialog.dismiss();

        userDetails = user;

        String repos = user.getRepos() + " Repositories";
        String _followers = user.getFollowers() + " Followers";

        Picasso.Builder builder = new Picasso.Builder(getApplicationContext());
        builder.downloader(new OkHttp3Downloader(getApplicationContext()));
        builder.build().load(profPic)
                .placeholder(R.drawable.ppic)
                .error(R.drawable.ic_launcher_foreground)
                .into(profileImage);

        url.setText(urlData);
        username.setText(userName);
        location.setText(user.getLocation());
        company.setText(user.getOrganization());
        repo.setText(repos);
        followers.setText(_followers);

        share = findViewById(R.id.share);

        onClickShare();
    }

    public void onClickShare() {

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = "Check out this awesome developer @" + userName + ", " + urlData + ".";

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(intent, "Share user profile via..."));
            }
        });
    }
}
