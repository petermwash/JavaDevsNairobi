package pemwa.com.javadevsnairobi.view

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

import pemwa.com.javadevsnairobi.R
import pemwa.com.javadevsnairobi.model.UserDetails
import pemwa.com.javadevsnairobi.presenter.DetailPresenter

class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var profileImage: ImageView
    internal lateinit var share: ImageView
    internal lateinit var username: TextView
    private lateinit var company: TextView
    private lateinit var url: TextView
    private lateinit var followers: TextView
    private lateinit var repo: TextView
    private lateinit var location: TextView

    private lateinit var detailPresenterView: DetailPresenterView
    private var userDetails: UserDetails? = null
    internal var userName: String? = null
    private var profPic: String? = null
    private var urlData: String? = null

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_detail)

        progressDialog = ProgressDialog(this@DetailActivity)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        detailPresenterView = DetailPresenter(this)

        profileImage = findViewById(R.id.userProfile)
        username = findViewById(R.id.userName)
        url = findViewById(R.id.userGithubLink)
        repo = findViewById(R.id.userRepos)
        followers = findViewById(R.id.userFollowers)
        location = findViewById(R.id.userLocation)
        company = findViewById(R.id.userOrganisation)


        val intent = intent
        val bundle = intent.getBundleExtra("data")
        profPic = bundle.getString("profPic")
        userName = bundle.getString("userName")
        urlData = bundle.getString("url")

        if (savedInstanceState != null) {
            userDetails = savedInstanceState.getParcelable(USER_KEY)
            this.displayUserDetail(userDetails!!)
        } else {
            detailPresenterView.getUserDetails(userName!!)
        }

        url.setOnClickListener {
            val userProfile = Intent(Intent.ACTION_VIEW, Uri.parse(urlData))
            startActivity(userProfile)
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)

        savedInstanceState.putParcelable(USER_KEY, userDetails)
    }

    override fun displayUserDetail(user: UserDetails) {

        progressDialog.dismiss()

        userDetails = user

        val repos = user.repos + " Repositories"
        val _followers = user.followers + " Followers"

        val builder = Picasso.Builder(applicationContext)
        builder.downloader(OkHttp3Downloader(applicationContext))
        builder.build().load(profPic)
                .placeholder(R.drawable.ppic)
                .error(R.drawable.ic_launcher_foreground)
                .into(profileImage)

        url.text = urlData
        username.text = userName
        location.text = user.location
        company.text = user.organization
        repo.text = repos
        followers.text = _followers

        share = findViewById(R.id.share)

        onClickShare()
    }

    fun onClickShare() {

        share.setOnClickListener {
            val message = "Check out this awesome developer @$userName, $urlData."

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, message)
            startActivity(Intent.createChooser(intent, "Share user profile via..."))
        }
    }

    companion object {

        internal const val USER_KEY = "user"
    }
}
