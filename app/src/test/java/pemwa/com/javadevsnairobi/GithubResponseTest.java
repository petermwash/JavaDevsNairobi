package pemwa.com.javadevsnairobi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import pemwa.com.javadevsnairobi.model.GithubUsers;
import pemwa.com.javadevsnairobi.model.GithubUsersResponse;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class GithubResponseTest {

    private String username = "username";
    private String userProfile = "userProfile";
    private String url = "url";
    private String followers = "followers";
    private String repos = "repos";

    private GithubUsersResponse githubUsersResponse = new GithubUsersResponse();
    private GithubUsers githubUsers = new GithubUsers(username, userProfile, url, followers, repos);
    private GithubUsers githubUsers1 = new GithubUsers(username, userProfile, url, followers, repos);
    private ArrayList<GithubUsers> githubUsersArrayList = new ArrayList<>();

    @Test
    public void testGithubResponse() {

        githubUsersArrayList.add(githubUsers);
        githubUsersArrayList.add(githubUsers1);
        githubUsersResponse.setUsers(githubUsersArrayList);
        githubUsersResponse.setTotal_count("2");

        assertEquals("2", githubUsersResponse.getTotal_count());
        assertArrayEquals(new ArrayList[]{githubUsersArrayList}, new List[]{githubUsersResponse.getUsers()});
    }
}
