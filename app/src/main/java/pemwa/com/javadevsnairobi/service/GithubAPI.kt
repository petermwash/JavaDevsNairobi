package pemwa.com.javadevsnairobi.service

import pemwa.com.javadevsnairobi.model.GithubUsersResponse
import pemwa.com.javadevsnairobi.model.UserDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {

    @GET("search/users?q=language:java+location:nairobi")
    fun getUsers(): Call<GithubUsersResponse>

    @GET("users/{user}")
    fun getUser(@Path("user") user: String): Call<UserDetails>

}
