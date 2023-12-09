package com.example.socialspin.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.socialspin.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class SocialSpinViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(User())
    val uiState :StateFlow<User> = _uiState.asStateFlow()
    val auth = firebaseauth.getAuth()
    fun signIn(email: String,password: String)
    {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {task->
                if(task.isSuccessful)
                {
                    Log.d("USER","User created successfuly")
                    val userCurrent  =auth.currentUser
                }
                else
                {
                    Log.w("USER","User creation failed")
                }
            }
    }
    fun getFireBaseAuth() :FirebaseAuth
    {
        return auth
    }
    fun userLoggedInStatus(): Boolean
    {
        if(auth.currentUser!=null)
        {
            return true
        }
        else
        {
            return false
        }
    }

    fun signOutUser()
    {
        auth.signOut()
    }
    fun logIn(email: String,password: String)
    {
        //auth = Firebase.auth
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task->
                if(task.isSuccessful)
                {
                    Log.d("USER","User LogedIn successFully")
                }
                else
                {
                    Log.w("USER","User failed to login")
                }
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
object firebaseauth{
    private lateinit var auth : FirebaseAuth
    init {
        auth = Firebase.auth
    }
    fun getAuth(): FirebaseAuth
    {
        return auth
    }
}