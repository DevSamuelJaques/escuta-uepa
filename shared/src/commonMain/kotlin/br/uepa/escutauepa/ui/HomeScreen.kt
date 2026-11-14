package br.uepa.escutauepa.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    nomeUsuario: String,
    onSair: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bem-vindo, $nomeUsuario!",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(16.dp))

        Text("Você está conectado ao EscutaUEPA.")

        Spacer(Modifier.height(24.dp))

        Button(onClick = onSair) {
            Text("Sair")
        }
    }
}