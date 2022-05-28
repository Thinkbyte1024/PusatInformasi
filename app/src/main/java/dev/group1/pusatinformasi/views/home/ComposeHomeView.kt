package dev.group1.pusatinformasi.views.home

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import  androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.group1.pusatinformasi.LoginActivity
import dev.group1.pusatinformasi.R

class ComposeHomeView(private val context:Context){
    @Composable
    fun Homepage() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {Text("Homepage", color = Color.White)},
                    backgroundColor = colorResource(id = R.color.primary_color),
                    actions = {
                        IconButton(
                            onClick = {
                                context.startActivity(Intent(context, LoginActivity::class.java))
                            }
                        ) {
                            Icon(Icons.Filled.AccountCircle, contentDescription = "", tint = Color.White)
                        }
                    }
                )},
            bottomBar = {
                //
            }
        ) {

        }
    }
}

