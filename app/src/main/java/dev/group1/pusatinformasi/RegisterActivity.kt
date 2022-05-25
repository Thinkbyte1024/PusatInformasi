package dev.group1.pusatinformasi

import android.os.Bundle
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
import dev.group1.pusatinformasi.ui.theme.PusatInformasiTheme
import dev.group1.pusatinformasi.views.auth.ComposeRegisterView

class RegisterActivity : ComponentActivity(), AuthView {
    private var authPresenter = AuthPresenter(this, this)
    private var composeregisterView = ComposeRegisterView(this, authPresenter)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PusatInformasiTheme(false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    composeregisterView.RegisterView()
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Register_View() {
        composeregisterView.RegisterView()
    }

    override fun onBadRequest(message: String) {
        setContent {
            composeregisterView.AlertDisplay(message = message)
        }
    }

    override fun onSuccessRegister(message: String) {
        setContent {
            composeregisterView.AlertDisplay(message = message)
        }
    }

    override fun onSuccessLogin(authResponse: AuthResponse) {}

}