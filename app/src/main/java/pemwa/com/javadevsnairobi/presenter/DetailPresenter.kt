package pemwa.com.javadevsnairobi.presenter

import pemwa.com.javadevsnairobi.model.UserDetails
import pemwa.com.javadevsnairobi.service.GithubService
import pemwa.com.javadevsnairobi.view.DetailPresenterView
import pemwa.com.javadevsnairobi.view.DetailView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(private val detailView: DetailView) : DetailPresenterView {

    private var githubService: GithubService? = null

    init {
        if (this.githubService == null) {
            this.githubService = GithubService()
        }
    }

    override fun getUserDetails(userName: String) {
        githubService!!
                .getGithubAPI()
                .getUser(userName)
                .enqueue(object : Callback<UserDetails> {
                    override fun onResponse(call: Call<UserDetails>, response: Response<UserDetails>) {
                        if (response.isSuccessful) {
                            detailView.displayUserDetail(response.body()!!)
                        }
                    }

                    override fun onFailure(call: Call<UserDetails>, t: Throwable) {
                        try {
                            throw InterruptedException("Something went wrong!")
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }

                    }
                })
    }
}
