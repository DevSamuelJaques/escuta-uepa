package br.uepa.escutauepa.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.uepa.escutauepa.viewmodel.HomeViewModel

private val VerdeUEPA = Color(0xFF007F77)
private val AzulEscuro = Color(0xFF172238)

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onCadastrar: () -> Unit,
    onHistorico: () -> Unit,
    onSair: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = VerdeUEPA
            ),
            shape = RoundedCornerShape(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "EscutaUEPA",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "SUA VOZ IMPORTA",
                    color = Color.White,
                    letterSpacing = 2.sp
                )
            }
        }

        Column {
            Text(
                text = "Olá, ${viewModel.obterNomeUsuario()}!",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = AzulEscuro
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "O que você deseja fazer hoje?",
                color = Color.Gray
            )
        }

        MenuCard(
            titulo = "Registrar manifestação",
            descricao = "Envie uma sugestão, reclamação ou denúncia.",
            simbolo = "+",
            onClick = onCadastrar
        )

        MenuCard(
            titulo = "Meu histórico",
            descricao = "Acompanhe suas manifestações e seus status.",
            simbolo = "≡",
            onClick = onHistorico
        )

        Spacer(Modifier.weight(1f, fill = false))

        OutlinedButton(
            onClick = {
                viewModel.sair()
                onSair()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text("Sair da conta", color = VerdeUEPA)
        }
    }
}

@Composable
private fun MenuCard(
    titulo: String,
    descricao: String,
    simbolo: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = simbolo,
                fontSize = 30.sp,
                color = VerdeUEPA
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = titulo,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = AzulEscuro
                )

                Spacer(Modifier.height(5.dp))

                Text(
                    text = descricao,
                    color = Color.Gray,
                    fontSize = 13.sp
                )
            }

            Text("›", fontSize = 28.sp, color = VerdeUEPA)
        }
    }
}