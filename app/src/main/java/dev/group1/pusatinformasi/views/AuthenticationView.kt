package dev.group1.pusatinformasi.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.group1.pusatinformasi.R
import dev.group1.pusatinformasi.netService
import dev.group1.pusatinformasi.network.model.AuthResponse
import dev.group1.pusatinformasi.network.model.LoginCredentials
import dev.group1.pusatinformasi.network.model.RegisterCredentials
import io.ktor.client.call.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// ================================================================================================
// Halaman Login
// ================================================================================================

@Composable
fun LoginView(navController: NavController) {
    var response: HttpResponse
    var errorMessage = remember { mutableStateOf("") }

    // Dialog
    val dialogState = remember { mutableStateOf(false) }

    val messageDialogState = remember { mutableStateOf(false) }

    // Variabel untuk menamplikan/Menyembunyikan password
    var passwordVisible by remember { mutableStateOf(false) }

    // Variabel input
    var emailInput by remember { mutableStateOf("") }

    var passwordInput = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login", style = MaterialTheme.typography.h4)

        Column {
            OutlinedTextField(
                label = { Text(text = "Nama pengguna") },
                modifier = Modifier.padding(8.dp),
                singleLine = true,
                value = emailInput,
                onValueChange = {
                    emailInput = it
                }
            )

            PasswordTextfield(
                visibleState = passwordVisible,
                inputState = passwordInput,
                text = "Kata sandi"
            )

//            OutlinedTextField(
//                label = { Text(text = "Kata sandi") },
//                modifier = Modifier.padding(8.dp),
//                singleLine = true,
//                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
//                value = passwordInput,
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                onValueChange = {
//                    passwordInput = it
//                },
//                trailingIcon = {
//                    val image = if (passwordVisible.value)
//                        R.drawable.ic_baseline_visibility_24
//                    else R.drawable.ic_baseline_visibility_off_24
//
//                    // Please provide localized description for accessibility services
//                    val description = if (passwordVisible.value) "Hide password" else "Show password"
//
//                    IconButton(onClick = {passwordVisible.value = !passwordVisible.value}){
//                        Icon(painter = painterResource(id = image), description)
//                    }
//                }
//            )
        }

        Button(shape = MaterialTheme.shapes.medium, onClick = {
            // TODO: Tambahkan fail-safe jika pengguna tidak memasukkan nilai pada salah satu kolom input.
            dialogState.value = true

            GlobalScope.launch(Dispatchers.Main) {
                // Pemanggilan API bersama dengan body JSON
                response = netService.login(
                    LoginCredentials(
                        email = emailInput,
                        password = passwordInput.value
                    )
                )

                if (response.status.value in 200..299) {
                    dialogState.value = false
                    navController.popBackStack(ViewRoutes.HomePage.route, false)
                } else if (response.status.value in 400..499) {
                    println("Error: ${response.status.value}\nResponse: ${response.body<String>()}")
                    val errorBody = response.body<AuthResponse>()

                    dialogState.value = false
                    messageDialogState.value = true
                    errorMessage.value =
                        (errorBody.message?.replaceFirstChar(Char::titlecase) ?: String) as String
                }
            }
        }) {
            Text(text = "Login")
        }

        TextButton(onClick = {
            navController.navigate(ViewRoutes.RegisterPage.route)
        }) {
            Text(text = "Daftar")
        }
    }

    // Progress dialog
    DialogComponents.AuthenticationProgressDialog(
        dialogState = dialogState,
        message = "Loading..."
    )

    // Error message dialog
    DialogComponents.MessageDialog(
        dialogState = messageDialogState,
        message = errorMessage.value
    )
}

// ================================================================================================
// Halaman Daftar
// ================================================================================================

@Composable
fun RegisterView(navController: NavController) {
    var response: HttpResponse
    var errorMessage = remember { mutableStateOf("") }

    // State dialog
    val dialogState = remember { mutableStateOf(false) }
    val messageDialogState = remember { mutableStateOf(false) }

    // Variabel untuk menamplikan/Menyembunyikan password
    val passwordVisible by remember { mutableStateOf(false) }

    // Variabel input
    var nameInput by remember { mutableStateOf("") }

    var addressInput by remember { mutableStateOf("") }

    val passwordInput = remember { mutableStateOf("") }

    val verifyPass = remember { mutableStateOf("") }

    var emailInput by remember { mutableStateOf("") }

    // Desain UI
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Daftar", style = MaterialTheme.typography.h4)

        Column {
            OutlinedTextField(
                label = { Text(text = "Nama lengkap") },
                modifier = Modifier.padding(8.dp),
                singleLine = true,
                value = nameInput,
                onValueChange = {
                    nameInput = it
                }
            )

            PasswordTextfield(
                visibleState = passwordVisible,
                inputState = passwordInput,
                text = "Kata sandi"
            )
            PasswordTextfield(
                visibleState = passwordVisible,
                inputState = verifyPass,
                text = "Kata sandi ulang"
            )

            OutlinedTextField(
                label = { Text(text = "Email") },
                modifier = Modifier.padding(8.dp),
                singleLine = true,
                value = emailInput,
                onValueChange = {
                    emailInput = it
                }
            )

            OutlinedTextField(
                label = { Text(text = "Alamat") },
                modifier = Modifier.padding(8.dp),
                singleLine = true,
                value = addressInput,
                onValueChange = {
                    addressInput = it
                }
            )
        }

        Button(shape = MaterialTheme.shapes.medium, onClick = {
            // TODO: Tambahkan fail-safe jika pengguna tidak memasukkan nilai pada salah satu kolom input.
            if (passwordInput.value == verifyPass.value) {
                GlobalScope.launch(Dispatchers.Main) {
                    // Pemanggilan API bersama dengan body JSON
                    response = netService.register(
                        RegisterCredentials(
                            nama= nameInput,
                            alamat = addressInput,
                            email = emailInput,
                            password = passwordInput.value
                        )
                    )

                    // Memeriksa error code pada HTTP
                    if (response.status.value in 200..299) {
                        dialogState.value = false
                        navController.popBackStack(ViewRoutes.HomePage.route, false)
                    } else if (response.status.value in 400..499) {
                        val errorBody = response.body<AuthResponse>()

                        // Tampilkan dialog kesalahan
                        dialogState.value = false
                        messageDialogState.value = true
                        errorMessage.value =
                            (errorBody.message?.replaceFirstChar(Char::titlecase) ?: String) as String
                    }
                }
            }
        }) {
            Text(text = "Daftar")
        }
    }
    // Progress Dialogs
    DialogComponents.AuthenticationProgressDialog(
        dialogState = dialogState,
        message = "Loading..."
    )

    // Error message dialog
    DialogComponents.MessageDialog(
        dialogState = messageDialogState,
        message = errorMessage.value
    )
}

// ================================================================================================
// Textfield password
// ================================================================================================

@Composable
fun PasswordTextfield(
    visibleState: Boolean,
    inputState: MutableState<String>,
    text: String
) {
    var visible by remember {
        mutableStateOf(visibleState)
    }

    var input by remember {
        mutableStateOf(inputState.value)
    }

    OutlinedTextField(
        label = { Text(text = text) },
        modifier = Modifier.padding(8.dp),
        singleLine = true,
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        value = input,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        onValueChange = {
            input = it
            inputState.value = input
        },
        trailingIcon = {
            val image = if (visible)
                R.drawable.ic_baseline_visibility_24
            else R.drawable.ic_baseline_visibility_off_24

            val description = if (visible) "Hide password" else "Show password"

            IconButton(onClick = { visible = !visible }) {
                Icon(painter = painterResource(id = image), description)
            }
        }
    )
}

// ================================================================================================
// Fungsi-fungsi autentikasi (Tidak dipakai, mungkin bisa diperbaiki)
// ================================================================================================

//private fun requestLogin(
//    input: LoginCredentials,
//    dialogState: MutableState<Boolean>? = null,
//    alertState: MutableState<Boolean>? = null
//): AuthResponse {
//    var response: HttpResponse
//
//    return GlobalScope.launch(Dispatchers.Main) {
//        response = netService.login(input)
//
//        if (response.status.value in 200..299) {
//            if (dialogState?.value == true) {
//                dialogState.value = false
//            }
//
//            return@launch response.body()
//        }
//    }
//}

//private fun requestRegister(input: RegisterCredentials) = runBlocking {
//    netService.register(input)
//}

// ================================================================================================
// Jetpack Compose preview
// ================================================================================================

@Preview(showBackground = true)
@Composable
fun Login_Preview() {
    LoginView(rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun Register_View() {
    RegisterView(rememberNavController())
}