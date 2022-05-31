package dev.group1.pusatinformasi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.group1.pusatinformasi.network.config.NetConfig
import dev.group1.pusatinformasi.ui.theme.PusatInformasiTheme
import dev.group1.pusatinformasi.views.home.ComposeHomeView

// Membuat klien HTTP saat aplikasi dijalankan
val netService by lazy {
    NetConfig.create()
}

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

// ================================================================================================
// Navigasi utama
// ================================================================================================

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ViewRoutes.HomePage.route) {
        composable(route = ViewRoutes.HomePage.route) {
            Homepage(navController)
        }
        composable(route = ViewRoutes.LoginPage.route) {
            LoginView(navController = navController)
        }
        composable(route = ViewRoutes.RegisterPage.route) {
            RegisterView(navController = navController)
        }
    }
}

// ================================================================================================
// Halaman utama
// ================================================================================================

@Composable
fun Homepage(navController: NavController) {
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
                    navController.navigate(ViewRoutes.LoginPage.route)
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
        // TODO: Buat daftar halaman berita
    }
}

// ================================================================================================
// Jetpack Compose preview
// ================================================================================================

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PusatInformasiTheme {
        val navController = rememberNavController()
        Homepage(navController)
    }
}