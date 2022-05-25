package dev.group1.pusatinformasi.views.auth

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.group1.pusatinformasi.LoginActivity
import dev.group1.pusatinformasi.R
import dev.group1.pusatinformasi.RegisterActivity
import dev.group1.pusatinformasi.presenter.AuthPresenter

class ComposeRegisterView (val context:Context, val authPresenter: AuthPresenter){

    @Composable
    fun RegisterView() {

        var namaInput by remember {
            mutableStateOf("")
        }
        var emailInput by remember {
            mutableStateOf("")
        }
        var passwordInput by remember {
            mutableStateOf("")
        }
        var alamatInput by remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Daftar", style = MaterialTheme.typography.h4)

            Column {
                OutlinedTextField(
                    label = { Text(text = "Nama lengkap") },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(1f)
                    ,
                    value = namaInput,
                    onValueChange = {
                        namaInput = it
                    }
                )

                OutlinedTextField(
                    label = { Text(text = "Alamat") },
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .fillMaxWidth(1f),
                    value = alamatInput,
                    onValueChange = {
                        alamatInput = it
                    }
                )

                OutlinedTextField(
                    label = { Text(text = "Email") },
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .fillMaxWidth(1f),
                    value = emailInput,
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
                    onValueChange = {
                        passwordInput = it
                    }
                )
            }

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
                        namaInput.isEmpty()->{
                            Toast.makeText(context, "inputan nama masih kosong", Toast.LENGTH_SHORT).show()
                        }
                        alamatInput.isEmpty()->{
                            Toast.makeText(context, "inputan alamat masih kosong", Toast.LENGTH_SHORT).show()
                        }
                        emailInput.isEmpty()->{
                            Toast.makeText(context, "inputan email masih kosong", Toast.LENGTH_SHORT).show()
                        }
                        passwordInput.isEmpty()->{
                            Toast.makeText(context, "inputan password masih kosong", Toast.LENGTH_SHORT).show()
                        }
                        else->{
                            authPresenter.register(
                                namaInput,
                                emailInput,
                                passwordInput,
                                alamatInput
                            )
                        }
                    }
                }) {
                Text(text = "Daftar")
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
                    context.startActivity(Intent(context, LoginActivity::class.java))
                }) {
                Text(text = "Login")
            }
        }
    }

    @Composable
    fun AlertDisplay(message:String){
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                TextButton(onClick = {
                    context.startActivity(Intent(context, RegisterActivity::class.java))
                })
                { Text(text = "OK") }
            },
            title = { Text("Message")},
            text = { Text(message)}
        )
    }
}