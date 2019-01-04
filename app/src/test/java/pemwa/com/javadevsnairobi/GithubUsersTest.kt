package pemwa.com.javadevsnairobi

import org.junit.Test

import pemwa.com.javadevsnairobi.model.GithubUsers

import org.junit.Assert.assertEquals

class GithubUsersTest {

    private val username = "username"
    private val userProfile = "userProfile"
    private val url = "url"
    private val followers = "followers"
    private val repos = "repos"

    private val githubUsers = GithubUsers(username, userProfile, url, followers, repos)

    @Test
    fun testUserData() {
        assertEquals(username, githubUsers.userName)
        assertEquals(userProfile, githubUsers.profilePic)
        assertEquals(url, githubUsers.url)
        assertEquals(followers, githubUsers.followers)
        assertEquals(repos, githubUsers.repos)
    }
}
