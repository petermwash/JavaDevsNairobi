package pemwa.com.javadevsnairobi.presenter

import android.util.Log

import java.util.ArrayList

import pemwa.com.javadevsnairobi.model.GithubUsers
import pemwa.com.javadevsnairobi.model.GithubUsersResponse
import pemwa.com.javadevsnairobi.service.GithubService
import pemwa.com.javadevsnairobi.view.UsersPresenterView
import pemwa.com.javadevsnairobi.view.UsersView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import android.content.ContentValues.TAG

class GithubPresenter(private val usersView: UsersView) : UsersPresenterView {

    private var githubService: GithubService? = null

    init {
        if (this.githubService == null) {
            this.githubService = GithubService()
        }
    }

    override fun getGithubUsers() {
        githubService!!
                .getGithubAPI()
                .getUsers()
                .enqueue(object : Callback<GithubUsersResponse> {
                    override fun onResponse(call: Call<GithubUsersResponse>, response: Response<GithubUsersResponse>) {
                        if (response.isSuccessful) {
                            val users = response.body()!!.users

                            if (users != null) {
                                Log.i(TAG, "onResponse: there is some data received")
                                usersView.displayGithubUsers(users)
                            }
                        }
                    }

                    override fun onFailure(call: Call<GithubUsersResponse>, t: Throwable) {
                        try {
                            throw InterruptedException("Something went wrong!")
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }

                    }
                })
    }
}
