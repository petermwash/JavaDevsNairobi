package pemwa.com.javadevsnairobi;

import org.junit.Test;

import pemwa.com.javadevsnairobi.model.GithubUsers;

import static org.junit.Assert.assertEquals;

public class GithubUsersTest {

    private String username = "username";
    private String userProfile = "userProfile";
    private String url = "url";
    private String followers = "followers";
    private String repos = "repos";

    private GithubUsers githubUsers = new GithubUsers(username, userProfile, url, followers, repos);

    @Test
    public void testUserData() {
        assertEquals(username, githubUsers.getUserName());
        assertEquals(userProfile, githubUsers.getProfilePic());
        assertEquals(url, githubUsers.getUrl());
        assertEquals(followers, githubUsers.getFollowers());
        assertEquals(repos, githubUsers.getRepos());
    }
}
