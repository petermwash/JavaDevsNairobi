package pemwa.com.javadevsnairobi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import pemwa.com.javadevsnairobi.R;

public class DetailActivity extends AppCompatActivity {

    ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_detail);

        profileImage = findViewById(R.id.userProfile);
    }
}