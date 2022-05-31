package dev.group1.pusatinformasi.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

// ================================================================================================
// Komponen dialog
// ================================================================================================

class DialogComponents {
    companion object {
        @Composable
        fun AuthenticationProgressDialog(
            dialogState: MutableState<Boolean>,
            message: String
        ) {
            if (dialogState.value) {
                Dialog(onDismissRequest = { /*TODO*/ }) {
                    Card(
                        shape = MaterialTheme.shapes.medium,
                        backgroundColor = Color(255, 255, 255),
                        elevation = 8.dp
                    ) {
                        Row(
                            modifier = Modifier.padding(start = 16.dp, end = 36.dp, top = 16.dp, bottom = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            CircularProgressIndicator(modifier = Modifier.padding(end = 16.dp))
                            Text(
                                modifier = Modifier.padding(start = 8.dp),
                                text = message,
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }
            }
        }

        @Composable
        fun MessageDialog(
            dialogState: MutableState<Boolean>,
            message: String
        ) {
            if (dialogState.value) {
                AlertDialog(
                    onDismissRequest = { },
                    text = { Text(text = message) },
                    confirmButton = {
                        Button(onClick = { dialogState.value = false }) {
                            Text(text = "OK")
                        }
                    }
                )
            }
        }
    }
}

// ================================================================================================
// Jetpack Compose preview
// ================================================================================================

@Preview(showBackground = true)
@Composable
fun AuthenticationDialog_Preview() {
    DialogComponents.AuthenticationProgressDialog(dialogState = remember {
        mutableStateOf(true)
    }, message = "Test")
}

@Preview(showBackground = true)
@Composable
fun MessageDialog_Preview() {
    DialogComponents.MessageDialog(dialogState = remember {
        mutableStateOf(true)
    }, message = "Message")
}