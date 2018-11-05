package pemwa.com.javadevsnairobi.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import pemwa.com.javadevsnairobi.R;
import pemwa.com.javadevsnairobi.adapter.GithubAdapter;
import pemwa.com.javadevsnairobi.model.GithubUsers;
import pemwa.com.javadevsnairobi.presenter.GithubPresenter;

public class MainActivity extends AppCompatActivity implements UsersView, SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private GithubAdapter adapter;
    ArrayList<GithubUsers> githubUsersArrayList;
    UsersPresenterView usersPresenterView;
    ProgressDialog progressDialog;
    GridLayoutManager gridLayoutManager;
    Parcelable parcelable;
    SwipeRefreshLayout swipeRefreshLayout;
    final String USERS_KEY = "users";
    public final static String RECYCLER_VIEW_STATE_KEY = "recycler_view_state";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimaryDark,
                R.color.colorPrimary,
                R.color.colorAccent);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        recyclerView = findViewById(R.id.customRecyclerView);
        usersPresenterView = new GithubPresenter(this);
        gridLayoutManager = new GridLayoutManager(this, 2);

        if (savedInstanceState != null) {
//            githubUsersArrayList = new ArrayList<>();
            githubUsersArrayList = savedInstanceState.getParcelableArrayList(USERS_KEY);
            displayGithubUsers(githubUsersArrayList);
        } else {
            usersPresenterView.getGithubUsers();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (parcelable != null) {
            gridLayoutManager.onRestoreInstanceState(parcelable);
        }
        Log.i(TAG, "onResume is called");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList(USERS_KEY, this.githubUsersArrayList);

        parcelable = gridLayoutManager.onSaveInstanceState();
        savedInstanceState.putParcelable(RECYCLER_VIEW_STATE_KEY, parcelable);
        Log.i(TAG, "onSaveInstanceState is called");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            parcelable = savedInstanceState.getParcelable(RECYCLER_VIEW_STATE_KEY);
        }
        Log.i(TAG, "onRestoreInstanceState is called");
    }

    @Override
    public void displayGithubUsers(ArrayList<GithubUsers> usersArrayList) {

        progressDialog.dismiss();
        swipeRefreshLayout.setRefreshing(false);

        githubUsersArrayList = usersArrayList;
        adapter = new GithubAdapter(usersArrayList, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.notifyDataSetChanged();

        lauchUserDetails();
    }

    public void lauchUserDetails() {
        recyclerView.addOnItemTouchListener(new ItemClickView(getApplicationContext(), new ItemClickView.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                GithubUsers user = githubUsersArrayList.get(position);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("profPic", user.getProfilePic());
                bundle.putString("userName", user.getUserName());
                bundle.putString("url", user.getUrl());
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        }));
    }

    @Override
    public void onRefresh() {
        usersPresenterView.getGithubUsers();
    }
}
