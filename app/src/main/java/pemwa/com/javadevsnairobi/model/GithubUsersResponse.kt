package pemwa.com.javadevsnairobi.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.ArrayList


class GithubUsersResponse {

    @SerializedName("items")
    @Expose
    var users: ArrayList<GithubUsers>? = null

    @SerializedName("total_count")
    @Expose
    var total_count: String? = null

    constructor() {}

    constructor(users: ArrayList<GithubUsers>, total_count: String) {
        this.users = users
        this.total_count = total_count
    }
}
