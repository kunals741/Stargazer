package com.kunal.stargazer.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kunal.stargazer.domain.GraphQLRepository
import com.kunal.stargazer.ui.Action
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val graphQLRepository: GraphQLRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileState.initial)
    val uiState = _uiState.asStateFlow()

    init {
        handleAction(ProfileAction.LoadUserProfile)
    }

    fun handleAction(action: Action) {
        when (action) {
            ProfileAction.LoadUserProfile, ProfileAction.RetryUserProfile -> {
                loadUserProfile()
            }
        }
    }

    private fun loadUserProfile() = viewModelScope.launch {
        _uiState.update {
            it.copy(isLoading = true, errorMessage = null)
        }
        graphQLRepository.getUserProfile().fold(
            onSuccess = { userProfile ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        userProfile = userProfile,
                        errorMessage = null
                    )
                }
            },
            onFailure = { throwable ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        userProfile = null,
                        errorMessage = throwable.message ?: "Something went wrong"
                    )
                }
            }
        )
    }
}
