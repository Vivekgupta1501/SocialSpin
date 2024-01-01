package com.example.socialspin.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialspin.R
import com.example.socialspin.model.User
import com.example.socialspin.viewModel.ScreenViewModel
import com.example.socialspin.viewModel.SocialSpinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    viewModel : SocialSpinViewModel,
    screenViewModel: ScreenViewModel,
    user :User,
    modifier : Modifier = Modifier
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
                value = user.name,
                onValueChange = {viewModel.updateName(it)},
                label = {
                    Text(text = stringResource(id = R.string.name))
                },
                leadingIcon ={
                    Image(
                        painter = painterResource(id = R.drawable.baseline_person_24),
                        contentDescription = "Entre the name"
                    )
                },
                trailingIcon ={
                    if(!user.name.isEmpty())
                    {
                        IconButton(onClick = { viewModel.clearName() }) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = "erase the name"
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
                value = user.age.toString(),
                onValueChange = {viewModel.updateAge(it)},
                label = {
                    Text(text = stringResource(id = R.string.age))
                },
                leadingIcon ={
                    Image(
                        painter = painterResource(id = R.drawable.baseline_person_24),
                        contentDescription = "Entre the name"
                    )
                },
                trailingIcon ={
                    if(!user.age.isEmpty())
                    {
                        IconButton(onClick = { viewModel.clearAge() }) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = "erase the age"
                            )

                        }
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, bottom = 10.dp, end = 20.dp, top = 10.dp)
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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
                        IconButton(onClick = { viewModel.clearPassword() }) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = "erase the password"
                            )

                        }
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, bottom = 10.dp, end = 20.dp, top = 10.dp)
            )
            OutlinedTextField(
                value = user.confirmPassword,
                onValueChange = {viewModel.updateConfirmPassword(it)},
                label = {
                    Text(text = stringResource(id = R.string.confirm_password))
                },
                leadingIcon ={
                    Image(
                        painter = painterResource(id = R.drawable.baseline_lock_24),
                        contentDescription = "Entre the email"
                    )
                },
                trailingIcon ={
                    if(!user.confirmPassword.isEmpty())
                    {
                        IconButton(onClick = { viewModel.clearConfirmPassword() }) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = "erase the email"
                            )

                        }
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, bottom = 10.dp, end = 20.dp, top = 10.dp)
            )
            Button(
                onClick = {
                    Log.d("USER","Button CLicked")
                    viewModel.signIn(user.email,user.password,{
                        if(screenViewModel.userLogedInStatus())
                        {
                            screenViewModel.toHomeScreen()
                        }

                    }
                        )
                          },
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
            )
            {
                Text(text = stringResource(id = R.string.signin))
            }
            Text(text = "Already Registered")
            Text(text = "Login", fontStyle = FontStyle.Italic, modifier = Modifier.clickable {
                screenViewModel.toLoginScreen()
            }
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SignInScreenPreview()
{
SignInScreen(SocialSpinViewModel(), ScreenViewModel(),User())
}