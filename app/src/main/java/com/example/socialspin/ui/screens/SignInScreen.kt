package com.example.socialspin.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialspin.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(modifier : Modifier = Modifier)
{
    var email:String = ""
    var password: String  = ""
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
                value = email,
                onValueChange = {email = it},
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
                    if(!email.toString().isEmpty())
                    {
                        IconButton(onClick = { email = "" }) {
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
                value = password,
                onValueChange = {password = it},
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
                    if(!password.toString().isEmpty())
                    {
                        IconButton(onClick = { password = "" }) {
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
            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
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
                    if(!password.toString().isEmpty())
                    {
                        IconButton(onClick = { password = "" }) {
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
                onClick = { /*TODO*/ },
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
            )
            {
                Text(text = stringResource(id = R.string.login))
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SignInScreenPreview()
{
SignInScreen()
}