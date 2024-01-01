package com.example.socialspin.viewModel


import android.icu.util.Calendar
import android.text.format.DateUtils
import android.util.Log
import androidx.compose.foundation.pager.PageSize
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.socialspin.model.Post
import com.example.socialspin.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking


class SocialSpinViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(User())
    val uiState :StateFlow<User> = _uiState.asStateFlow()
    /*
    private val _postState = MutableStateFlow(Post())
    val postState :StateFlow<Post> = _postState.asStateFlow()
    */
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    val auth = firebaseauth.getAuth()
    val db  =Firebase.firestore



    private val database = FirebaseDatabase.getInstance()
    private val postsRef = database.getReference("Posts")
    private val postListener = object : ValueEventListener{
        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

        override fun onDataChange(snapshot: DataSnapshot) {
            val updatedPosts = snapshot.children.mapNotNull { it.getValue(Post::class.java) }
            _posts.value = updatedPosts.reversed()
        }

    }
    init {
        retrievePosts()
    }


    fun createPost(postText: String) {
        getUserdata()
        val postId = postsRef.push().key
        val timestamp = System.currentTimeMillis()
        val post = Post(
            autherId = auth.currentUser?.uid.toString(),
            autherName =uiState.value.name.toString() ,
            postId = postId.toString(),
            postText = postText,
            timeStamp = timestamp
        )
        postsRef.child(postId ?: "").setValue(post)
    }

    private fun retrievePosts() {
        postsRef.addValueEventListener(postListener)
    }
    override fun onCleared() {
        // Remove the ValueEventListener to avoid memory leaks
        postsRef.removeEventListener(postListener)
        super.onCleared()
    }
    //use to convert time in human readable form
    fun timeAgo(timestamp: Long): String {
        val now = System.currentTimeMillis()
        val timeAgo = DateUtils.getRelativeTimeSpanString(timestamp, now, DateUtils.MINUTE_IN_MILLIS)
        return timeAgo.toString()
    }
    fun createUser() {
        val userRefrence = database.getReference("User/"+auth.currentUser?.uid.toString())
        userRefrence.setValue(uiState).addOnCompleteListener {task->
            if(task.isSuccessful)
            {
                Log.d("USER","Real time user added")
            }
            else
            {
                Log.w("USER","Real time user creation failed-"+task.exception.toString())
            }
        }

    }

    fun signIn(email: String,password: String,forStatus: () -> Unit)
    {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {task->
                if(task.isSuccessful)
                {
                    Log.d("USER","User created successfuly")
                    val userCurrent  =auth.currentUser
                    createUser()
                    db.collection("Users").document(auth.currentUser?.uid.toString())
                        .set(uiState)
                        .addOnCompleteListener {task->
                            if(task.isSuccessful)
                            {
                                forStatus()

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

       var userDetails: Map<String, String>
       // var userDetails: User =User()
        val docref = db.collection("Users").document(auth.currentUser?.uid.toString())
        runBlocking {
            docref.get()
                .addOnCompleteListener {task->
                    if(task.isSuccessful)
                    {
                        val document = task.result
                        Log.d("USER","User data retrived ${task.result.data}")
                        //val user = task.result.data?.get("value") as? String
                        //var user :String = document.getLong("name").toString()
                        userDetails  = document.data?.get("value") as Map<String,String>
                        updateUser(userDetails)

                    }
                    else
                    {
                        Log.w("USER",task.exception.toString())
                    }
                }
        }

    }

    fun updateUser(userDetails: Map<String,String>)
    {
        _uiState.update {
            it.copy(
                name = userDetails.get("name")!!,
                age = userDetails.get("age")!!

            )
        }
    }
    fun logIn(email: String,password: String,forStatus: ()->Unit)
    {
        //auth = Firebase.auth
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task->
                if(task.isSuccessful)
                {
                    runBlocking {
                        getUserdata()
                        forStatus()
                    }


                    Log.d("USER","User LogedIn successFully")
                }
                else
                {
                    Log.w("USER","User failed to login")
                }
            }.addOnCompleteListener {

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