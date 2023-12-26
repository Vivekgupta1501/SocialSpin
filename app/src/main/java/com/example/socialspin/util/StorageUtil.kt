package com.example.socialspin.util

import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.example.socialspin.viewModel.SocialSpinViewModel
import com.google.firebase.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import java.util.UUID

class StorageUtil {
    companion object{
        fun uploadToStorage(uri : Uri,context: Context,type: String,viewModel: SocialSpinViewModel)
        {
            val storage = Firebase.storage
            val storageRef = storage.reference
            val auth  = viewModel.auth
            val unique_image_name  = auth.currentUser?.uid

            val spaceRef : StorageReference

            if(type =="image")
            {
                spaceRef = storageRef.child("profileImages/$unique_image_name.jpg")

            }
            else
            {
                spaceRef  = storageRef.child("videos/$unique_image_name.mp4")
            }

            val byteArray :ByteArray? = context.contentResolver
                .openInputStream(uri)
                ?.use { it.readBytes() }

            byteArray?.let {
                val uploadTask = spaceRef.putBytes(byteArray)
                uploadTask.addOnFailureListener{
                    Toast.makeText(context,"Upload File failed",Toast.LENGTH_SHORT).show()
                }.addOnSuccessListener {taskSnapShot->
                    Toast.makeText(context,"Upload successfull",Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}