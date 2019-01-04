package pemwa.com.javadevsnairobi.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GithubUsers : Parcelable {

    @SerializedName("login")
    @Expose
    var userName: String? = null

    @SerializedName("avatar_url")
    @Expose
    var profilePic: String? = null

    @SerializedName("html_url")
    @Expose
    var url: String? = null

    @SerializedName("followers")
    @Expose
    var followers: String? = null

    @SerializedName("public_repos")
    @Expose
    var repos: String? = null

    constructor(userName: String, profilePic: String, url: String, followers: String, repos: String) {
        this.userName = userName
        this.profilePic = profilePic
        this.url = url
        this.followers = followers
        this.repos = repos
    }

    protected constructor(`in`: Parcel) {
        userName = `in`.readString()
        profilePic = `in`.readString()
        url = `in`.readString()
        followers = `in`.readString()
        repos = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(userName)
        dest.writeString(profilePic)
        dest.writeString(url)
        dest.writeString(followers)
        dest.writeString(repos)
    }

//    companion object {
//
//        val CREATOR: Parcelable.Creator<GithubUsers> = object : Parcelable.Creator<GithubUsers> {
//            override fun createFromParcel(source: Parcel): GithubUsers {
//                return GithubUsers(source)
//            }
//
//            override fun newArray(size: Int): Array<GithubUsers?> {
//                return arrayOfNulls(size)
//            }
//        }
//    }

    companion object CREATOR : Parcelable.Creator<GithubUsers> {
        override fun createFromParcel(parcel: Parcel): GithubUsers {
            return GithubUsers(parcel)
        }

        override fun newArray(size: Int): Array<GithubUsers?> {
            return arrayOfNulls(size)
        }
    }
}
