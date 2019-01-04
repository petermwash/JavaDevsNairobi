package pemwa.com.javadevsnairobi.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserDetails : Parcelable {

    @SerializedName("company")
    @Expose
    var organization: String? = null

    @SerializedName("location")
    @Expose
    var location: String? = null

    @SerializedName("followers")
    @Expose
    var followers: String? = null

    @SerializedName("public_repos")
    @Expose
    var repos: String? = null

    constructor() {}

    constructor(organization: String, location: String, followers: String, repos: String) {
        this.organization = organization
        this.location = location
        this.followers = followers
        this.repos = repos
    }

    private constructor(source: Parcel) {
        organization = source.readString()
        location = source.readString()
        followers = source.readString()
        repos = source.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(organization)
        dest.writeString(location)
        dest.writeString(followers)
        dest.writeString(repos)
    }

//    companion object {
//
//        val CREATOR: Parcelable.Creator<UserDetails> = object : Parcelable.Creator<UserDetails> {
//            override fun createFromParcel(source: Parcel): UserDetails {
//                return UserDetails(source)
//            }
//
//            override fun newArray(size: Int): Array<UserDetails> {
//                return arrayOfNulls(size)
//            }
//        }
//    }

    companion object CREATOR : Parcelable.Creator<UserDetails> {
        override fun createFromParcel(parcel: Parcel): UserDetails {
            return UserDetails(parcel)
        }

        override fun newArray(size: Int): Array<UserDetails?> {
            return arrayOfNulls(size)
        }
    }
}
