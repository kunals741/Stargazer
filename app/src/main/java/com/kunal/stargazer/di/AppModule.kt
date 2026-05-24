package com.kunal.stargazer.di

import com.apollographql.apollo.ApolloClient
import com.kunal.stargazer.BuildConfig
import com.kunal.stargazer.data.GraphQLRepositoryImpl
import com.kunal.stargazer.domain.GraphQLRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesApolloClient(): ApolloClient = ApolloClient.Builder()
        .serverUrl("https://api.github.com/graphql")
        .addHttpHeader(
            name = "Authorization",
            value = " Bearer ${BuildConfig.GITHUB_TOKEN}"
        ).build()

    @ViewModelScoped
    @Provides
    fun providesGraphQLRepository(apolloClient: ApolloClient): GraphQLRepository =
        GraphQLRepositoryImpl(apolloClient)
}
