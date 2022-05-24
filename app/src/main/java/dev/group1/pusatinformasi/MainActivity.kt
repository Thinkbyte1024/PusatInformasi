package dev.group1.pusatinformasi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.group1.pusatinformasi.ui.theme.PusatInformasiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PusatInformasiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Homepage()
                }
            }
        }
    }
}

@Composable
fun Homepage() {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Homepage",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                TextButton(modifier = Modifier.padding(8.dp), onClick = {

                }) {
                    Text(
                        "Login",
                        Modifier.padding(horizontal = 8.dp),
                        textAlign = TextAlign.Start
                    )
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Login Button"
                    )
                }
            }
        },
        bottomBar = {

        }
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PusatInformasiTheme {
        Homepage()
    }
}