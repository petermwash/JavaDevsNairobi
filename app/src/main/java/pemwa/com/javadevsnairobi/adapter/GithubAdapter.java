package pemwa.com.javadevsnairobi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pemwa.com.javadevsnairobi.R;
import pemwa.com.javadevsnairobi.model.GithubUsers;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.CustomViewHolder> {

    private ArrayList<GithubUsers> githubUsersArrayList;
    private Context context;

    public GithubAdapter (ArrayList<GithubUsers> githubUsersArrayList, Context context) {
        this.githubUsersArrayList = githubUsersArrayList;
        this.context = context;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageView profPic;
        TextView userName;
        TextView link;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            profPic = itemView.findViewById(R.id.profImage);
            userName = itemView.findViewById(R.id.username);
            link = itemView.findViewById(R.id.profileurl);
        }
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.custom_grid, viewGroup, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int position) {

        customViewHolder.userName.setText(githubUsersArrayList.get(position).getUserName());
        customViewHolder.link.setText(githubUsersArrayList.get(position).getUrl());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(githubUsersArrayList.get(position).getProfilePic())
                .placeholder(R.drawable.ppic)
                .error(R.drawable.ic_launcher_foreground)
                .into(customViewHolder.profPic);
    }

    @Override
    public int getItemCount() {
        return githubUsersArrayList.size();
    }
}
