package pemwa.com.javadevsnairobi.view

import android.app.ProgressDialog
import android.content.Intent
import android.os.Parcelable
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View

import java.util.ArrayList

import pemwa.com.javadevsnairobi.R
import pemwa.com.javadevsnairobi.adapter.GithubAdapter
import pemwa.com.javadevsnairobi.model.GithubUsers
import pemwa.com.javadevsnairobi.presenter.GithubPresenter
import pemwa.com.javadevsnairobi.util.NetworkUtility

class MainActivity : AppCompatActivity(), UsersView, SwipeRefreshLayout.OnRefreshListener {
    private var recyclerView: RecyclerView? = null
    private var adapter: GithubAdapter? = null
    private var githubUsersArrayList: ArrayList<GithubUsers>? = null
    private lateinit var usersPresenterView: UsersPresenterView
    private lateinit var progressDialog: ProgressDialog
    private lateinit var gridLayoutManager: GridLayoutManager
    private var parcelable: Parcelable? = null
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.swipeRefresh)
        swipeRefreshLayout.setOnRefreshListener(this)
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimaryDark,
                R.color.colorPrimary,
                R.color.colorAccent)

        progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        recyclerView = findViewById(R.id.customRecyclerView)
        usersPresenterView = GithubPresenter(this)
        gridLayoutManager = GridLayoutManager(this, 2)

        if (savedInstanceState != null) {
            githubUsersArrayList = ArrayList()
            githubUsersArrayList = savedInstanceState.getParcelableArrayList(USERS_KEY)
            displayGithubUsers((githubUsersArrayList as ArrayList<GithubUsers>?)!!)
        } else {
            loadUsers()
        }
    }

    fun loadUsers() {
        if (!NetworkUtility.isConnected(this)) {
            progressDialog.dismiss()
            showSnackBar()
        } else {
            usersPresenterView.getGithubUsers()
        }
    }

    override fun onResume() {
        super.onResume()

        if (parcelable != null) {
            gridLayoutManager.onRestoreInstanceState(parcelable)
        }
        Log.i(TAG, "onResume is called")
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putParcelableArrayList(USERS_KEY, this.githubUsersArrayList)

        parcelable = gridLayoutManager.onSaveInstanceState()
        savedInstanceState.putParcelable(RECYCLER_VIEW_STATE_KEY, parcelable)
        Log.i(TAG, "onSaveInstanceState is called")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState != null) {
            parcelable = savedInstanceState.getParcelable(RECYCLER_VIEW_STATE_KEY)
        }
        Log.i(TAG, "onRestoreInstanceState is called")
    }

    override fun displayGithubUsers(usersArrayList: ArrayList<GithubUsers>) {

        progressDialog.dismiss()
        swipeRefreshLayout.isRefreshing = false

        githubUsersArrayList = usersArrayList
        adapter = GithubAdapter(usersArrayList, applicationContext)
        recyclerView!!.adapter = adapter
        recyclerView!!.layoutManager = gridLayoutManager
        adapter!!.notifyDataSetChanged()

        launchUserDetails()
    }

    private fun launchUserDetails() {
        recyclerView!!.addOnItemTouchListener(ItemClickView(applicationContext, object : ItemClickView.onItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val user = githubUsersArrayList!![position]

                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                val bundle = Bundle()
                bundle.putString("profPic", user.profilePic)
                bundle.putString("userName", user.userName)
                bundle.putString("url", user.url)
                intent.putExtra("data", bundle)
                return startActivity(intent)
            }
        }))
    }

    override fun onRefresh() {
        usersPresenterView.getGithubUsers()
    }

    private fun showSnackBar() {
        Snackbar.make(
                findViewById(R.id.snackbar),
                "Failed to load users!",
                Snackbar.LENGTH_LONG)
                .setAction("TAP TO RETRY") { loadUsers() }
                .show()
    }

    companion object {

        private val TAG = "MainActivity"
        val USERS_KEY = "users"
        val RECYCLER_VIEW_STATE_KEY = "recycler_view_state"
    }
}
