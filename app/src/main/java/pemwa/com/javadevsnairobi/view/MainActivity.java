package pemwa.com.javadevsnairobi.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import pemwa.com.javadevsnairobi.R;
import pemwa.com.javadevsnairobi.presenter.GithubPresenter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private static final String TAG = "MainActivity" ;
    CardView click;

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

        recyclerView = findViewById(R.id.customRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(null);

        GithubPresenter githubPresenter = new GithubPresenter();

        // Maybe it's best to call it on onResume()
        Log.i(TAG, "onCreate: before get");
        githubPresenter.getGithubUsers();
    }
}
