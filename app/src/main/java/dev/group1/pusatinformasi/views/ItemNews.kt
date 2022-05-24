package dev.group1.pusatinformasi.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ItemNews() {
    Row() {
        Image(modifier = Modifier.size(42.dp),imageVector = Icons.Default.AccountBox, contentDescription = "Berita")
        Column() {
            Text(text = "Judul")
            Text(style = MaterialTheme.typography.subtitle1,text = "Tanggal")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemNews_Preview(){
    ItemNews()
}