package com.example.socialspin.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.socialspin.R
import com.example.socialspin.model.User
import com.example.socialspin.viewModel.SocialSpinViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(viewModel:SocialSpinViewModel,user: User)
{
    var postText by remember {
      mutableStateOf<String> ("")
    }
    var postTextEmpty by remember {
        mutableStateOf<Boolean>(false)
    }
    val context  = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = postText,
            onValueChange = {postText = it},
            label = {
                Text(text ="Entre the post")
            },
            isError = postTextEmpty && postText.isEmpty(),
            supportingText = {
                if(postTextEmpty && postText.isEmpty())
                {
                    Text(text = "Post can not be empty ")
                }
            }
        )
        Button(onClick = {
            if(postText.isEmpty())
            {
                postTextEmpty = true
            }
            else
            {
                Toast.makeText(context,"Post is getting uploaded",Toast.LENGTH_SHORT).show()
                viewModel.createPost(postText)

            }
        }) {
            Text(text = "Post")
        }
    }
}