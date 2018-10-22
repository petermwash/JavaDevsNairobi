package pemwa.com.javadevsnairobi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GithubUsers {

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
}