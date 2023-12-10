package com.example.socialspin.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.socialspin.R
import com.example.socialspin.model.User
import com.example.socialspin.viewModel.SocialSpinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: SocialSpinViewModel,
    user  :User,
    modifier: Modifier = Modifier
)
{

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo of the app",
                modifier =Modifier.size(124.dp)
            )
            OutlinedTextField(
                value = user.email,
                onValueChange = {viewModel.updateEmail(it)},
                label = {
                    Text(text = stringResource(id = R.string.email))
                    },
                leadingIcon ={
                    Image(
                        painter = painterResource(id = R.drawable.baseline_email_24),
                        contentDescription = "Entre the email"
                        )
                },
                trailingIcon ={
                    if(!user.email.isEmpty())
                    {
                        IconButton(onClick = { viewModel.clearEmail() }) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = "erase the email"
                            )
                            
                        }
                    }
                },
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, bottom = 10.dp, end = 20.dp, top = 10.dp)
                )
            OutlinedTextField(
                value = user.password,
                onValueChange = {viewModel.updatePassword(it)},
                label = {
                    Text(text = stringResource(id = R.string.password))
                },
                leadingIcon ={
                    Image(
                        painter = painterResource(id = R.drawable.baseline_lock_24),
                        contentDescription = "Entre the email"
                    )
                },
                trailingIcon ={
                    if(!user.password.isEmpty())
                    {
                        IconButton(onClick = { viewModel.clearPassword()}) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = "erase the email"
                            )

                        }
                    }
                },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, bottom = 10.dp, end = 20.dp, top = 10.dp)
            )
            Button(
                onClick = { viewModel.logIn(user.email,user.password) },
                modifier= Modifier.fillMaxWidth()
                    .padding(start = 20.dp,end = 20.dp,top = 10.dp, bottom = 10.dp)
            )
            {
                Text(text = stringResource(id = R.string.login))
            }
            Text(text = "Not Registered Yet!")
            Text(text = "Register", fontStyle = FontStyle.Italic, modifier = Modifier.clickable {
                viewModel.toSignInScreen()
            })

        }
    }
}
//update 10-12-23
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview()
{
    LoginScreen(SocialSpinViewModel(),User())
}