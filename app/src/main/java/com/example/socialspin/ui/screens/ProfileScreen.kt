package com.example.socialspin.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.socialspin.model.User
import com.example.socialspin.util.StorageUtil
import com.example.socialspin.viewModel.ScreenViewModel
import com.example.socialspin.viewModel.SocialSpinViewModel

@Composable
fun ProfileScreen(
    viewModel: SocialSpinViewModel,
    user: User,
    screenViewModel: ScreenViewModel
)
{
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Hi!  ${user.name}")
        SinglePhotoPicker(viewModel)
        Button(onClick = {
            viewModel.signOutUser()
            if(!screenViewModel.userLogedInStatus())
            {
                screenViewModel.toLoginScreen()

            }
        }) {
            Text(text = "SignOut")
        }

    }
}

@Composable
fun SinglePhotoPicker(viewModel: SocialSpinViewModel)
{
    var uri by remember{
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult ={
            uri = it
        }
    )
    val context = LocalContext.current

    Column {
        Button(
            onClick = {
                singlePhotoPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        ) {
            Text(text = "Pick a Image")
        }
        AsyncImage(model = uri, contentDescription = null, modifier = Modifier.size(248.dp))
        Button(onClick = {
            uri?.let {
                StorageUtil.uploadToStorage(uri = it, context = context,type  ="image", viewModel = viewModel)
            }
        }) {
            Text("Upload")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview()
{
    ProfileScreen(SocialSpinViewModel(), screenViewModel = ScreenViewModel(), user = User())
}