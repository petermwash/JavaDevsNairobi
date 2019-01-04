package pemwa.com.javadevsnairobi

import org.junit.Test

import java.util.ArrayList

import pemwa.com.javadevsnairobi.model.GithubUsers
import pemwa.com.javadevsnairobi.model.GithubUsersResponse

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals

class GithubResponseTest {

    private val username = "username"
    private val userProfile = "userProfile"
    private val url = "url"
    private val followers = "followers"
    private val repos = "repos"

    private val githubUsersResponse = GithubUsersResponse()
    private val githubUsers = GithubUsers(username, userProfile, url, followers, repos)
    private val githubUsers1 = GithubUsers(username, userProfile, url, followers, repos)
    private val githubUsersArrayList = ArrayList<GithubUsers>()

    @Test
    fun testGithubResponse() {

        githubUsersArrayList.add(githubUsers)
        githubUsersArrayList.add(githubUsers1)
        githubUsersResponse.users = githubUsersArrayList
        githubUsersResponse.total_count = "2"

        assertEquals("2", githubUsersResponse.total_count)
        assertArrayEquals(arrayOf<ArrayList<*>>(githubUsersArrayList), arrayOf<List<*>>(githubUsersResponse.users!!))
    }
}
