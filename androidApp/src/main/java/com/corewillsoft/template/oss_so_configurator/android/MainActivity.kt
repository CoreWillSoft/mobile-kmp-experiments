package com.corewillsoft.template.oss_so_configurator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.corewillsoft.template.oss_so_configurator.Repository
import com.corewillsoft.template.oss_so_configurator.cache.AppDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Color(0xFFBB86FC),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    } else {
        lightColors(
            primary = Color(0xFF6200EE),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

class MainActivity : ComponentActivity() {

    private val databaseRepository by lazy {
        Repository.create(object : Repository.DriverFactory {
            override fun createDriver() =
                AndroidSqliteDriver(AppDatabase.Schema, application, "test.db")
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ExperimentalContent()
                }
            }
        }
    }

    @Composable
    fun ExperimentalContent() {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var rocketsCount by remember { mutableStateOf(0) }
            var rocketsXml by remember { mutableStateOf("Test") }

            Button(onClick = {
                databaseRepository.insertRocket(Math.random().toString())
                    .onEach { rocketsCount += 1 }
                    .launchIn(lifecycleScope)
            }) {
                Text(text = "Add rocket")
            }

            Button(onClick = {
                databaseRepository.getAllRockets()
                    .onEach { rocketsCount = it.size }
                    .launchIn(lifecycleScope)
            }) {
                Text(text = "Get rockets count")
            }

            Button(onClick = {
                databaseRepository.removeAllRockets()
                    .onEach { rocketsCount = 0 }
                    .launchIn(lifecycleScope)
            }) {
                Text(text = "Remove all rockets")
            }

            Button(onClick = {
                databaseRepository.generateAllRocketsXml()
                    .onEach { rocketsXml = it }
                    .launchIn(lifecycleScope)
            }) {
                Text(text = "Generate xml")
            }

            Text(
                text = "$rocketsCount rockets added",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )

            Text(
                text = rocketsXml,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.body1,
            )
        }
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        MyApplicationTheme {
            ExperimentalContent()
        }
    }
}
