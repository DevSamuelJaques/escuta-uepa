package br.uepa.escutauepa.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.uepa.escutauepa.viewmodel.LoginViewModel

private val VerdeUEPA = Color(0xFF087F76)
private val AzulEscuro = Color(0xFF172238)
private val Fundo = Color(0xFFF5F7FA)

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Fundo
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "EscutaUEPA",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = VerdeUEPA
            )

            Text(
                text = "SUA VOZ IMPORTA",
                fontSize = 12.sp,
                color = Color.Gray,
                letterSpacing = 2.sp
            )

            Spacer(Modifier.height(40.dp))

            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Boas-vindas",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzulEscuro
                    )

                    Text(
                        text = "Entre com seus dados institucionais.",
                        color = Color.Gray
                    )

                    OutlinedTextField(
                        value = viewModel.email,
                        onValueChange = viewModel::atualizarEmail,
                        label = { Text("E-mail") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp)
                    )

                    OutlinedTextField(
                        value = viewModel.senha,
                        onValueChange = viewModel::atualizarSenha,
                        label = { Text("Senha") },
                        singleLine = true,
                        visualTransformation =
                            PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp)
                    )

                    viewModel.mensagemErro?.let { erro ->
                        Text(
                            text = erro,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 13.sp
                        )
                    }

                    Button(
                        onClick = viewModel::entrar,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = VerdeUEPA
                        )
                    ) {
                        Text("Entrar", fontSize = 16.sp)
                    }

                    Text(
                        text = "Acesso demonstrativo para fins acadêmicos.",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}