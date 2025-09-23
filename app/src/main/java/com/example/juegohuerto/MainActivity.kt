package com.example.juegohuerto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.juegohuerto.ui.theme.JuegoHuertoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
}

@Composable
fun InterfazHuerto(name: String, modifier: Modifier = Modifier) {
    var dinero by remember { mutableIntStateOf(1) }
    var faseImagen by remember { mutableIntStateOf(0) }


    androidx.compose.foundation.layout.Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //Texto puntos
        Text(
            text = "Dinero: $dinero"
        )
        //Botón sumar puntos
        Button(onClick = {
            if (faseImagen < 2 ) {
                faseImagen++
            }

        }) {
            Text("Regar huerto")
        }

        //Botón vender huerto
        Button(onClick = {
            if (faseImagen == 2) {
                faseImagen = 0
                dinero++
            }
        }) {
            Text("Vender huerto")
        }

        //Imágenes
        val imagenesPlanta = listOf(
            R.drawable.plantafase1,
            R.drawable.plantafase2,
            R.drawable.plantafase3
        )

        Image(
            painter = painterResource(id = imagenesPlanta[faseImagen]),
            contentDescription = "Planta fase ${faseImagen + 1}"
        )

    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JuegoHuertoTheme {
        InterfazHuerto("Android")
    }
}