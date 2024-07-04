package com.modelo.ec2_mendoza_franklin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.modelo.ec2_mendoza_franklin.ui.theme.Ec2mendozafranklinTheme

data class Evento(val titulo: String, val descripcion: String, val fecha: String)

class ListadoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ec2mendozafranklinTheme {
                ListadoScreen(navController = rememberNavController())
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListadoScreen(navController: NavController) {
    val eventos = remember {
        listOf(
            Evento("conferencia", "tecnología", "01/01/2024"),
            Evento("cumbre", "medio ambiente", "02/02/2024"),
            Evento("salud", "medicina", "03/03/2024"),
            Evento("festival Cine", "cine internacional", "04/04/2024"),
            Evento("energias", "energias limpias", "05/05/2024"),
            Evento("lideres", "reunion global", "06/06/2024"),
            Evento("feria", "electronica", "07/07/2024"),
            Evento("congreso", "innovacion", "08/08/2024"),
            Evento("turismo", "destinos", "09/09/2024"),
            Evento("foro", "finanzas.", "10/10/2024"),
            Evento("ciencia", "cientificos.", "11/11/2024"),
            Evento("arte", "cultural.", "12/12/2024")
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Listado de Eventos") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                items(eventos) { evento ->
                    EventoCard(evento)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    )
}

@Composable
fun EventoCard(evento: Evento) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = evento.titulo,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = evento.descripcion,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = evento.fecha,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListadoPreview() {
    Ec2mendozafranklinTheme {
        ListadoScreen(navController = rememberNavController())
    }
}