package br.uepa.escutauepa

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import br.uepa.escutauepa.service.AutenticacaoService
import br.uepa.escutauepa.ui.HomeScreen
import br.uepa.escutauepa.ui.LoginScreen
import br.uepa.escutauepa.viewmodel.LoginViewModel

@Composable
@Preview
fun App() {
    val autenticacaoService = remember {
        AutenticacaoService()
    }

    val loginViewModel = remember {
        LoginViewModel(autenticacaoService)
    }

    MaterialTheme {
        val usuario = loginViewModel.usuarioAutenticado

        if (usuario == null) {
            LoginScreen(loginViewModel)
        } else {
            HomeScreen(
                nomeUsuario = usuario.nome,
                onSair = loginViewModel::sair
            )
        }
    }
}