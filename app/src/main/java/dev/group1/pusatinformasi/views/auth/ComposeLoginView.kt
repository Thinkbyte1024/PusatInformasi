package dev.group1.pusatinformasi.views.auth

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.group1.pusatinformasi.LoginActivity
import dev.group1.pusatinformasi.MainActivity
import dev.group1.pusatinformasi.R
import dev.group1.pusatinformasi.RegisterActivity
import dev.group1.pusatinformasi.presenter.AuthPresenter

class ComposeLoginView (private val context: Context, val authPresenter: AuthPresenter){

    @Composable
    fun LoginView() {
        var emailInput by remember {
            mutableStateOf("")
        }
        var passwordInput by remember {
            mutableStateOf("")
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "Login", style = MaterialTheme.typography.h4)

            Column {
                OutlinedTextField(
                    label = { Text(text = "Email") },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(1f)
                    ,
                    value = emailInput,
                    leadingIcon= {
                        Icon(imageVector = Icons.Filled.Email, contentDescription = "")
                    },
                    onValueChange = {
                        emailInput = it
                    }
                )

                OutlinedTextField(
                    label = { Text(text = "Password") },
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .fillMaxWidth(1f),
                    value = passwordInput,
                    visualTransformation = PasswordVisualTransformation(),
                    leadingIcon= {
                        Icon(imageVector = Icons.Filled.Lock, contentDescription = "")
                    },
                    onValueChange = {
                        passwordInput = it
                    }
                )

                Spacer(modifier = Modifier.padding(20.dp))

                Button(
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.primary_color),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(width = 0.dp, height = 46.dp),
                    onClick = {
                        when{
                            emailInput.isEmpty()->{
                                Toast.makeText(context, "inputan email masih kosong", Toast.LENGTH_SHORT).show()
                            }
                            passwordInput.isEmpty()->{
                                Toast.makeText(context, "inputan password masih kosong", Toast.LENGTH_SHORT).show()
                            }
                            else->{
                                authPresenter.login(
                                    emailInput,
                                    passwordInput
                                )
                            }
                        }
                    }) {
                    Text(text = "Login")
                }

                Spacer(modifier = Modifier.padding(8.dp))

                OutlinedButton(
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor=Color.White,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(width = 0.dp, height = 46.dp),
                    onClick = {
                        context.startActivity(Intent(context, RegisterActivity::class.java))
                    }) {
                    Text(text = "Daftar")
                }

                Spacer(modifier = Modifier.padding(8.dp))

            }
            ClickableText(
                text = AnnotatedString("Back to home"),
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp
                ),
                onClick = {
                    context.startActivity(Intent(context, MainActivity::class.java))
                }
            )

        }
    }

    @Composable
    fun AlertDisplay(message:String){
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                TextButton(onClick = {
                    context.startActivity(Intent(context, LoginActivity::class.java))
                })
                { Text(text = "OK") }
            },
            title = { Text("Message")},
            text = { Text(message)}
        )
    }
}


