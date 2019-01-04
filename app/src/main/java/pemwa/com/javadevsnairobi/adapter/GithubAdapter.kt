package pemwa.com.javadevsnairobi.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

import java.util.ArrayList

import pemwa.com.javadevsnairobi.R
import pemwa.com.javadevsnairobi.model.GithubUsers

class GithubAdapter(private val githubUsersArrayList: ArrayList<GithubUsers>, private val context: Context) : RecyclerView.Adapter<GithubAdapter.CustomViewHolder>() {

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var profPic: ImageView
        internal var userName: TextView
        internal var link: TextView

        init {

            profPic = itemView.findViewById(R.id.profImage)
            userName = itemView.findViewById(R.id.username)
            link = itemView.findViewById(R.id.profileurl)
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val view = layoutInflater.inflate(R.layout.custom_grid, viewGroup, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(customViewHolder: CustomViewHolder, position: Int) {

        customViewHolder.userName.text = githubUsersArrayList[position].userName
        customViewHolder.link.text = githubUsersArrayList[position].url

        val builder = Picasso.Builder(context)
        builder.downloader(OkHttp3Downloader(context))
        builder.build().load(githubUsersArrayList[position].profilePic)
                .placeholder(R.drawable.ppic)
                .error(R.drawable.ic_launcher_foreground)
                .into(customViewHolder.profPic)
    }

    override fun getItemCount(): Int {
        return githubUsersArrayList.size
    }
}
