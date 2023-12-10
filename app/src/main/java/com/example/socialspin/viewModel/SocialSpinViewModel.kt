package com.example.socialspin.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.socialspin.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.getField
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class SocialSpinViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(User())
    val uiState :StateFlow<User> = _uiState.asStateFlow()
    val auth = firebaseauth.getAuth()
    val db  =Firebase.firestore
    fun signIn(email: String,password: String)
    {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {task->
                if(task.isSuccessful)
                {
                    Log.d("USER","User created successfuly")
                    val userCurrent  =auth.currentUser
                    db.collection("Users").document(auth.currentUser?.uid.toString())
                        .set(uiState)
                        .addOnCompleteListener {task->
                            if(task.isSuccessful)
                            {
                                Log.d("USER","User added to database")
                            }
                            else
                            {
                                Log.w("USER","User not added to database-"+task.exception.toString())

                            }

                        }
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
    fun getUserdata()
    {
       // var userDetails: User =User()
        val docref = db.collection("Users").document(auth.currentUser?.uid.toString())
        docref.get()
            .addOnCompleteListener {task->
                if(task.isSuccessful)
                {
                    Log.d("USER","User data retrived ${task.result.data}")
                    val user = task.result.data?.get("name")
                    Log.d("USER","name is "+user?.name)
                    //return userDetails
                }
                else
                {
                    Log.w("USER",task.exception.toString())
                }
            }

    }
    fun updateUser(user:DocumentSnapshot)
    {

        _uiState.update {
            it.copy(
                name = user.getString("name").toString(),
                age = user.getString("age").toString(),
                email= user.getString("email").toString()
            )
        }
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
    fun updateName(inputString: String)
    {
        _uiState.update {
            it.copy(
                name = inputString
            )
        }
    }
    fun updateAge(input: String)
    {
        _uiState.update {
            it.copy(
                age = input
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

    fun clearName()
    {
        _uiState.update {
            it.copy(
                name = ""
            )
        }
    }
    fun clearAge()
    {
        _uiState.update {
            it.copy(
                age = ""
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