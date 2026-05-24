package com.kunal.stargazer.data

import com.apollographql.apollo.ApolloClient
import com.kunal.stargazer.ViewerQuery
import com.kunal.stargazer.domain.GraphQLRepository
import com.kunal.stargazer.domain.model.UserProfile
import javax.inject.Inject

class GraphQLRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : GraphQLRepository {
    override suspend fun getUserProfile(): Result<UserProfile> = runCatching {
        val response = apolloClient.query(ViewerQuery()).execute()

        if (response.hasErrors()) {
            throw (Exception(response.errors?.firstOrNull()?.message ?: "API Error"))
        }

        response.data?.viewer?.toUserProfile()
            ?: throw Exception("No data returned")
    }
}
