package com.example.socialspin.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialspin.R
import com.example.socialspin.model.Post
import com.example.socialspin.model.User
import com.example.socialspin.viewModel.SocialSpinViewModel

@Composable
fun HomeScreen(viewModel: SocialSpinViewModel)
{
    val posts by rememberUpdatedState(newValue = viewModel.posts.collectAsState().value)



    Column(modifier = Modifier.fillMaxHeight()) {

        LazyColumn{

            items(items = posts){post->
                PostItem(post = post,viewModel = viewModel)
            }
        }
    }
}

@Composable
fun PostItem(post: Post,viewModel: SocialSpinViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White),
        elevation = CardDefaults.cardElevation(pressedElevation = 24.dp, defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(2.dp)) {
            // Row 1: Creator's circular profile picture and author's name
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                Image(
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape) // Apply circular clip
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = post.autherName,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                 text = post.postText,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                IconButton(
                    onClick = {
                        // Handle liking the post
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Like"
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = viewModel.timeAgo(post.timeStamp).toString()
                )
            }
        }
    }
}

