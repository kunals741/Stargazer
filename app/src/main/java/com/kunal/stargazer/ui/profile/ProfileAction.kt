package com.kunal.stargazer.ui.profile

import com.kunal.stargazer.ui.Action

sealed interface ProfileAction : Action {
    data object LoadUserProfile : ProfileAction
    data object RetryUserProfile : ProfileAction
}