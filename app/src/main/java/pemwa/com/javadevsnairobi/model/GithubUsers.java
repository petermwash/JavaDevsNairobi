package pemwa.com.javadevsnairobi.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GithubUsers implements Parcelable {

    @SerializedName("login")
    @Expose
    private String userName;

    @SerializedName("avatar_url")
    @Expose
    private String profilePic;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("followers_url")
    @Expose
    private String followers;

    @SerializedName("repos_url")
    @Expose
    private String repos;

    public GithubUsers(String userName, String profilePic, String url, String followers, String repos) {
        this.userName = userName;
        this.profilePic = profilePic;
        this.url = url;
        this.followers = followers;
        this.repos = repos;
    }

    protected GithubUsers(Parcel in) {
        userName = in.readString();
        profilePic = in.readString();
        url = in.readString();
        followers = in.readString();
        repos = in.readString();
    }

    public static final Parcelable.Creator<GithubUsers> CREATOR = new Creator<GithubUsers>() {
        @Override
        public GithubUsers createFromParcel(Parcel source) {
            return new GithubUsers(source);
        }

        @Override
        public GithubUsers[] newArray(int size) {
            return new GithubUsers[size];
        }
    };

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getRepos() {
        return repos;
    }

    public void setRepos(String repos) {
        this.repos = repos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(profilePic);
        dest.writeString(url);
        dest.writeString(followers);
        dest.writeString(repos);
    }
}
