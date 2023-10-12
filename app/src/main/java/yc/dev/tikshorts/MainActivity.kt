package yc.dev.tikshorts

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import yc.dev.tikshorts.ui.theme.TikShortsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TikShortsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    SetupButtons()
                }
            }
        }
    }
}

@Composable
fun SetupButtons() {
    val context = LocalContext.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Button(
            onClick = { Toast.makeText(context, "Button A!", Toast.LENGTH_SHORT).show() },
        ) {
            Text(
                text = "Hello A!",
            )
        }

        Button(
            onClick = { Toast.makeText(context, "Button B!", Toast.LENGTH_SHORT).show() },
        ) {
            Text(
                text = "Hello B!",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TikShortsTheme {
        SetupButtons()
    }
}