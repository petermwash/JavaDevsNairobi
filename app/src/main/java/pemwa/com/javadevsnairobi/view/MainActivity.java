package pemwa.com.javadevsnairobi.view;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import pemwa.com.javadevsnairobi.R;
import pemwa.com.javadevsnairobi.model.GithubUsers;
import pemwa.com.javadevsnairobi.presenter.GithubPresenter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private static final String TAG = "MainActivity" ;
    CardView click;

    LinearLayoutManager linearLayoutManager;
    final String USERS_KEY = "users";
    public final static String RECYCLER_VIEW_STATE_KEY = "recycler_view_state";
    ArrayList<GithubUsers> githubUsersArrayList;
    Parcelable parcelable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click = findViewById(R.id.card);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(details);
            }
        });

        if (savedInstanceState != null) {
            githubUsersArrayList = new ArrayList<>();
            githubUsersArrayList = savedInstanceState.getParcelableArrayList(USERS_KEY);
            // Render the users list here
        }else {
            // Load the data from API here
        }

        recyclerView = findViewById(R.id.customRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(null);

        GithubPresenter githubPresenter = new GithubPresenter();

        // Maybe it's best to call it on onResume()
        Log.i(TAG, "onCreate: before get");
        githubPresenter.getGithubUsers();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (parcelable != null) {
            linearLayoutManager.onRestoreInstanceState(parcelable);
        }
        Log.i(TAG, "onResume is called");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        parcelable = linearLayoutManager.onSaveInstanceState();
        savedInstanceState.putParcelable(RECYCLER_VIEW_STATE_KEY, parcelable);
        Log.i(TAG, "onSaveInstanceState is called");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null){
            parcelable = savedInstanceState.getParcelable(RECYCLER_VIEW_STATE_KEY);
        }
        Log.i(TAG, "onRestoreInstanceState is called");
    }
}
