package com.kunal.stargazer.data

import android.net.Uri
import com.kunal.stargazer.ViewerQuery
import com.kunal.stargazer.domain.model.UserProfile

fun ViewerQuery.Viewer.toUserProfile(): UserProfile {
    return UserProfile(
        name = name ?: "No Name :(",
        githubId = login,
        bio = bio.orEmpty(),
        followerCount = followers.totalCount,
        followingCount = following.totalCount,
        avatarUrl = avatarUrl as Uri?,
        repositoriesCount = repositories.totalCount
    )
}
