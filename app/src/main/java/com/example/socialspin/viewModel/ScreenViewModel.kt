package com.example.socialspin.viewModel

import androidx.lifecycle.ViewModel
import com.example.socialspin.model.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ScreenViewModel() :ViewModel() {
    private val _uiState = MutableStateFlow(Screen())
    val uiState : StateFlow<Screen> = _uiState.asStateFlow()
    val auth = firebaseauth.getAuth()
    init {
        _uiState.update {
            it.copy(
                isUserLoggedIn = userLogedInStatus()
            )
        }
    }
    fun userLogedInStatus():Boolean {
        if (auth.currentUser != null)
        {
            return true
        }
        else
        {
            return false
        }
    }

    fun toLoginScreen()
    {
        _uiState.update {
            it.copy(
                isShowingLoginScreen = true,
                isUserLoggedIn = false
            )
        }
    }
    fun toRegisterScreen()
    {
        _uiState.update {
            it.copy(
                isShowingLoginScreen = false
            )
        }
    }
    fun toHomeScreen()
    {
        _uiState.update {
            it.copy(
                isUserLoggedIn = true
            )
        }
    }
}