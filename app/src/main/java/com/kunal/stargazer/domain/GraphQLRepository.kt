package com.kunal.stargazer.domain

import com.kunal.stargazer.domain.model.UserProfile

interface GraphQLRepository {
    suspend fun getUserProfile() : Result<UserProfile>
}
