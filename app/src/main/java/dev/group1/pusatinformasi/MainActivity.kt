package dev.group1.pusatinformasi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.group1.pusatinformasi.ui.theme.PusatInformasiTheme
import dev.group1.pusatinformasi.views.home.ComposeHomeView

class MainActivity : ComponentActivity() {
    private var composeHomeView = ComposeHomeView(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PusatInformasiTheme(false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.onPrimary
                ) {
                    composeHomeView.Homepage()
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        PusatInformasiTheme {
            composeHomeView.Homepage()
        }
    }
}