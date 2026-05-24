package com.kunal.stargazer.domain.model

import android.net.Uri

data class UserProfile(
    val name: String,
    val githubId: String,
    val bio: String,
    val followerCount: Int,
    val followingCount: Int,
    val avatarUrl: Uri?,
    val repositoriesCount: Int,
)
