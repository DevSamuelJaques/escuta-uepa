package br.uepa.escutauepa

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import br.uepa.escutauepa.repository.InMemoryManifestacaoRepository
import br.uepa.escutauepa.service.AutenticacaoService
import br.uepa.escutauepa.service.ManifestacaoService
import br.uepa.escutauepa.viewmodel.*
import br.uepa.escutauepa.ui.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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

    val repository = remember {
        InMemoryManifestacaoRepository()
    }

    val manifestacaoService = remember {
        ManifestacaoService(
            repository = repository,
            autenticacaoService = autenticacaoService
        )
    }

    val loginViewModel = remember {
        LoginViewModel(autenticacaoService)
    }

    val homeViewModel = remember {
        HomeViewModel(autenticacaoService)
    }

    val manifestacaoViewModel = remember {
        ManifestacaoViewModel(manifestacaoService)
    }

    val historicoViewModel = remember {
        HistoricoViewModel(manifestacaoService)
    }

    var telaAtual by remember {
        mutableStateOf(Tela.INICIAL)
    }

    MaterialTheme {
        val usuario = loginViewModel.usuarioAutenticado

        if (usuario == null) {
            LoginScreen(loginViewModel)
        } else {
            when (telaAtual) {
                Tela.INICIAL -> HomeScreen(
                    viewModel = homeViewModel,
                    onCadastrar = {
                        manifestacaoViewModel.limparFormulario()
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

                Tela.CADASTRO -> ManifestacaoScreen(
                    viewModel = manifestacaoViewModel,
                    autorId = usuario.id,
                    onVoltar = {
                        manifestacaoViewModel.limparFormulario()
                        telaAtual = Tela.INICIAL
                    },
                    onCadastroRealizado = {
                        telaAtual = Tela.INICIAL
                    }
                )

                Tela.HISTORICO -> HistoricoScreen(
                    viewModel = historicoViewModel,
                    onVoltar = {
                        historicoViewModel.fecharDetalhes()
                        telaAtual = Tela.INICIAL
                    }
                )
            }
        }
    }
}

@Composable
fun TelaProvisoria(
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

        Spacer(Modifier.height(16.dp))

        Text("Tela em desenvolvimento.")

        Spacer(Modifier.height(24.dp))

        Button(onClick = onVoltar) {
            Text("Voltar")
        }
    }
}