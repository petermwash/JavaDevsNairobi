package pemwa.com.javadevsnairobi.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetails implements Parcelable {

    @SerializedName("company")
    @Expose
    private String organization;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("followers")
    @Expose
    private String followers;

    @SerializedName("public_repos")
    @Expose
    private String repos;

    public UserDetails(){}

    public UserDetails(String organization, String location, String followers, String repos) {
        this.organization = organization;
        this.location = location;
        this.followers = followers;
        this.repos = repos;
    }

    private UserDetails(Parcel source) {
        organization = source.readString();
        location = source.readString();
        followers = source.readString();
        repos = source.readString();
    }

    public static final Creator<UserDetails> CREATOR = new Creator<UserDetails>() {
        @Override
        public UserDetails createFromParcel(Parcel source) {
            return new UserDetails(source);
        }

        @Override
        public UserDetails[] newArray(int size) {
            return new UserDetails[size];
        }
    };

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        dest.writeString(organization);
        dest.writeString(location);
        dest.writeString(followers);
        dest.writeString(repos);
    }
}
