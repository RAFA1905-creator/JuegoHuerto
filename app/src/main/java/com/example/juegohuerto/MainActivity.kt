package com.example.juegohuerto

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.juegohuerto.ui.theme.JuegoHuertoTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mediaPlayer = musica(this, R.raw.cancion)

        setContent {
            JuegoHuertoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InterfazHuerto(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release() // Liberar recursos
    }
}

@Composable
//Función interfaz del juego
fun InterfazHuerto(name: String, modifier: Modifier = Modifier) {
    var dinero by remember { mutableIntStateOf(1) }
    var faseImagen by remember { mutableIntStateOf(0) }

    val imagenesPlanta = listOf(
        R.drawable.plantafase1,
        R.drawable.plantafase2,
        R.drawable.plantafase3
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TEXTO DINERO ARRIBA
        Text(text = "Dinero: $dinero")

        // IMAGEN PLANTA EN EL MEDIO
        Image(
            painter = painterResource(id = imagenesPlanta[faseImagen]),
            contentDescription = "Planta fase ${faseImagen + 1}"
        )

        // BOTON INTERACTUAR CON PLANTA ABAJO
        Button(
            onClick = {
                if (faseImagen < 2) {
                    faseImagen++
                } else {
                    faseImagen = 0
                    dinero += Random.nextInt(1, 3)
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (faseImagen == 2) Color.Red else Color.Green,
                contentColor = Color.White
            ),
            shape = RectangleShape,
        ) {
            Text(text = if (faseImagen == 2) "Vender planta" else "Regar planta")
        }
    }
}

fun musica(context: Context, resId: Int): MediaPlayer {
    val mediaPlayer = MediaPlayer.create(context, resId)
    mediaPlayer.isLooping = true
    mediaPlayer.setVolume(1.0f, 1.0f)
    mediaPlayer.start()
    return mediaPlayer
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JuegoHuertoTheme {
        InterfazHuerto("Android")
    }
}
