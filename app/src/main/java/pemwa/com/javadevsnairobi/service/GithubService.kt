package pemwa.com.javadevsnairobi.service

import com.google.gson.GsonBuilder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubService {

    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://api.github.com/"


    fun getGithubAPI() : GithubAPI {
        val gson = GsonBuilder().setLenient().create()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
        }
        return retrofit!!.create(GithubAPI::class.java)
    }
}
