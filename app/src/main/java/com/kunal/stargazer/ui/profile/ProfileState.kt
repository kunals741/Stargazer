package com.kunal.stargazer.ui.profile

import com.kunal.stargazer.domain.model.UserProfile

data class ProfileState (
    val isLoading : Boolean,
    val userProfile : UserProfile?,
    val errorMessage : String?
){
    companion object {
        val initial = ProfileState(
            isLoading = false,
            userProfile = null,
            errorMessage = null
        )
    }
}