package com.example.socialspin.viewModel

import androidx.lifecycle.ViewModel
import com.example.socialspin.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SocialSpinViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(User())
    val uiState :StateFlow<User> = _uiState.asStateFlow()

    fun toLoginScreen() {
        _uiState.update {
            it.copy(
                isShowingLoginPage = true
            )
        }
    }
    fun toSignInScreen() {
        _uiState.update {
            it.copy(
                isShowingLoginPage = false
            )
        }
    }
    fun updateEmail(inputString: String)
    {
        _uiState.update {
            it.copy(
                email = inputString
            )
        }
    }
    fun updatePassword(inputString: String)
    {
        _uiState.update {
            it.copy(
                password = inputString
            )
        }
    }
    fun updateConfirmPassword(inputString: String)
    {
        _uiState.update {
            it.copy(
                confirmPassword =  inputString
            )
        }
    }
    fun clearEmail()
    {
        _uiState.update {
            it.copy(
                email = ""
            )
        }
    }
    fun clearPassword()
    {
        _uiState.update {
            it.copy(
                password = ""
            )
        }
    }
    fun clearConfirmPassword()
    {
        _uiState.update {
            it.copy(
                confirmPassword = ""
            )
        }
    }
 }