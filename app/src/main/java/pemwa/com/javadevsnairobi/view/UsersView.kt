package pemwa.com.javadevsnairobi.view

import java.util.ArrayList

import pemwa.com.javadevsnairobi.model.GithubUsers

interface UsersView {

    fun displayGithubUsers(usersArrayList: ArrayList<GithubUsers>)
}
