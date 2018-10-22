package pemwa.com.javadevsnairobi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class GithubUsersResponse {

    @SerializedName("items")
    @Expose
    ArrayList<GithubUsers> users;

    @SerializedName("total_count")
    @Expose
    private String total_count;

    public GithubUsersResponse(ArrayList<GithubUsers> users, String total_count) {
        this.users = users;
        this.total_count = total_count;
    }

    public ArrayList<GithubUsers> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<GithubUsers> users) {
        this.users = users;
    }

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }
}