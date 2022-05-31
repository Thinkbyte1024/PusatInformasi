package dev.group1.pusatinformasi

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.group1.pusatinformasi.model.AuthResponse
import dev.group1.pusatinformasi.presenter.AuthPresenter
import dev.group1.pusatinformasi.presenter.AuthView
import dev.group1.pusatinformasi.storage.SessionManager
import dev.group1.pusatinformasi.ui.theme.PusatInformasiTheme
import dev.group1.pusatinformasi.views.auth.ComposeLoginView

class LoginActivity : ComponentActivity(), AuthView {

    private var authPresenter = AuthPresenter(this, this)
    private var composeLoginView = ComposeLoginView(this, authPresenter)
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(this)
        setContent {
            PusatInformasiTheme(false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    composeLoginView.LoginView()
                }
            }
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview2() {
        PusatInformasiTheme {
            composeLoginView.LoginView()
        }
    }

    override fun onBadRequest(message: String) {
       setContent {
           composeLoginView.AlertDisplay(message = message)
       }
    }

    override fun onSuccessRegister(message: String) {}

    override fun onSuccessLogin(authResponse: AuthResponse) {
        // save token and name to session preference
        authResponse.nama?.let { sessionManager.setNama(it) }
        authResponse.token?.let { sessionManager.setToken(it) }
        Toast.makeText(this, "Login Sukses, Tokennya : "+authResponse.token, Toast.LENGTH_LONG).show()
        // to homepage activity
    }
}

