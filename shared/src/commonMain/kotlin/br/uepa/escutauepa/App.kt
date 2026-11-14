package br.uepa.escutauepa

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.uepa.escutauepa.service.AutenticacaoService
import br.uepa.escutauepa.ui.HomeScreen
import br.uepa.escutauepa.ui.LoginScreen
import br.uepa.escutauepa.viewmodel.HomeViewModel
import br.uepa.escutauepa.viewmodel.LoginViewModel

private enum class Tela {
    INICIAL,
    CADASTRO,
    HISTORICO
}

@Composable
fun App() {
    val autenticacaoService = remember {
        AutenticacaoService()
    }

    val loginViewModel = remember {
        LoginViewModel(autenticacaoService)
    }

    val homeViewModel = remember {
        HomeViewModel(autenticacaoService)
    }

    var telaAtual by remember {
        mutableStateOf(Tela.INICIAL)
    }

    MaterialTheme {
        if (loginViewModel.usuarioAutenticado == null) {
            LoginScreen(loginViewModel)
        } else {
            when (telaAtual) {
                Tela.INICIAL -> HomeScreen(
                    viewModel = homeViewModel,
                    onCadastrar = {
                        telaAtual = Tela.CADASTRO
                    },
                    onHistorico = {
                        telaAtual = Tela.HISTORICO
                    },
                    onSair = {
                        loginViewModel.sair()
                        telaAtual = Tela.INICIAL
                    }
                )

                Tela.CADASTRO -> TelaProvisoria(
                    titulo = "Registrar manifestação",
                    onVoltar = {
                        telaAtual = Tela.INICIAL
                    }
                )

                Tela.HISTORICO -> TelaProvisoria(
                    titulo = "Meu histórico",
                    onVoltar = {
                        telaAtual = Tela.INICIAL
                    }
                )
            }
        }
    }
}

@Composable
private fun TelaProvisoria(
    titulo: String,
    onVoltar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = titulo,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(12.dp))

        Text("Tela em desenvolvimento.")

        Spacer(Modifier.height(24.dp))

        Button(onClick = onVoltar) {
            Text("Voltar")
        }
    }
}