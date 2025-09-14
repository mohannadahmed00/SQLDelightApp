package org.example.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.style.TextDirection
import org.jetbrains.compose.resources.Font
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sqldelightproject.composeapp.generated.resources.Res
import sqldelightproject.composeapp.generated.resources.hafs_smart_08

@Composable
@Preview
fun App(viewModel: DemoViewModel) {
    MaterialTheme {
        val ayas by viewModel.state.collectAsState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .verticalScroll(
                    rememberScrollState()
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = ayas,
                fontFamily = FontFamily(Font(Res.font.hafs_smart_08)),
                fontSize = 20.sp,
                textAlign = TextAlign.Justify,
                style = TextStyle(textDirection = TextDirection.Rtl)
            )
        }
    }
}