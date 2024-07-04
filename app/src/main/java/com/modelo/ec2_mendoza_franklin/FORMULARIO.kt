package com.modelo.ec2_mendoza_franklin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.modelo.ec2_mendoza_franklin.ui.theme.Ec2mendozafranklinTheme

class FormularioActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ec2mendozafranklinTheme {
                FormularioScreen(navController = rememberNavController())
            }
        }
    }
}

@Composable
fun FormularioScreen(navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "CUESTIONARIO",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp),
                    textAlign = TextAlign.Center
                )


                Text(text = "1. Marque sus habilidades")
                val habilidades = listOf("Autoconocimiento", "Empatía", "Comunicación asertiva", "Toma de decisiones", "Pensamiento crítico", "Ninguno")
                val selectedHabilidades = remember { mutableStateListOf<String>() }
                habilidades.forEach { habilidad ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .toggleable(
                                value = selectedHabilidades.contains(habilidad),
                                onValueChange = {
                                    if (it) selectedHabilidades.add(habilidad)
                                    else selectedHabilidades.remove(habilidad)
                                }
                            )
                            .padding(vertical = 4.dp)
                    ) {
                        Checkbox(
                            checked = selectedHabilidades.contains(habilidad),
                            onCheckedChange = null
                        )
                        Text(
                            text = habilidad,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))


                val preguntas = listOf(
                    "2. ¿Cuán significativo es tu trabajo?" to listOf("Mucho", "Mas o menos", "Poco"),
                    "3. ¿Qué tan bien te pagan en el trabajo que haces?" to listOf("Bien", "Regular", "Mal"),
                    "4. ¿Trabajas bajo presión?" to listOf("Si", "No"),
                    "5. ¿Tienes oportunidad de crecimiento en tu trabajo?" to listOf("Si", "No")
                )
                val selectedOptions = remember { mutableStateMapOf<Int, String>() }

                preguntas.forEachIndexed { index, pregunta ->
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = pregunta.first)
                    pregunta.second.forEach { option ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = selectedOptions[index] == option,
                                    onClick = { selectedOptions[index] = option }
                                )
                                .padding(vertical = 4.dp)
                        ) {
                            RadioButton(
                                selected = selectedOptions[index] == option,
                                onClick = null
                            )
                            Text(
                                text = option,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Resolver",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun FormularioPreview() {
    Ec2mendozafranklinTheme {
        FormularioScreen(navController = rememberNavController())
    }
}