package pemwa.com.javadevsnairobi.view;

import android.app.ProgressDialog;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import pemwa.com.javadevsnairobi.R;
import pemwa.com.javadevsnairobi.adapter.GithubAdapter;
import pemwa.com.javadevsnairobi.model.GithubUsers;
import pemwa.com.javadevsnairobi.presenter.GithubPresenter;

public class MainActivity extends AppCompatActivity implements UsersView{

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private GithubAdapter adapter;
    ArrayList<GithubUsers> githubUsersArrayList;
    UsersPresenterView usersPresenterView;
    ProgressDialog progressDialog;
    LinearLayoutManager linearLayoutManager;
    Parcelable parcelable;
    final String USERS_KEY = "users";
    public final static String RECYCLER_VIEW_STATE_KEY = "recycler_view_state";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        recyclerView = findViewById(R.id.customRecyclerView);
        usersPresenterView = new GithubPresenter(this);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        if (savedInstanceState != null) {
            githubUsersArrayList = new ArrayList<>();
            this.githubUsersArrayList = savedInstanceState.getParcelableArrayList(USERS_KEY);
            Log.d(TAG, "onCreate: savedisnt null"+githubUsersArrayList);
            displayGithubUsers(githubUsersArrayList);
        }else {
            usersPresenterView.getGithubUsers();
        }
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
        savedInstanceState.putParcelableArrayList(USERS_KEY, this.githubUsersArrayList);

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

    @Override
    public void displayGithubUsers(ArrayList<GithubUsers> usersArrayList) {

        progressDialog.dismiss();

        githubUsersArrayList = usersArrayList;
        adapter = new GithubAdapter(usersArrayList, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.notifyDataSetChanged();
    }
}
