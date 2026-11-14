package br.uepa.escutauepa.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import br.uepa.escutauepa.model.Usuario
import br.uepa.escutauepa.service.AutenticacaoService

class LoginViewModel(
    private val autenticacaoService: AutenticacaoService
) {
    var email by mutableStateOf("")
        private set

    var senha by mutableStateOf("")
        private set

    var mensagemErro by mutableStateOf<String?>(null)
        private set

    var usuarioAutenticado by mutableStateOf<Usuario?>(null)
        private set

    fun atualizarEmail(valor: String) {
        email = valor
        mensagemErro = null
    }

    fun atualizarSenha(valor: String) {
        senha = valor
        mensagemErro = null
    }

    fun entrar() {
        if (email.isBlank() || senha.isBlank()) {
            mensagemErro = "Preencha e-mail e senha."
            return
        }

        val usuario = autenticacaoService.autenticar(
            email = email,
            senha = senha
        )

        if (usuario == null) {
            mensagemErro = "E-mail ou senha incorretos."
            return
        }

        usuarioAutenticado = usuario
        mensagemErro = null
        senha = ""
    }

    fun sair() {
        autenticacaoService.encerrarSessao()
        usuarioAutenticado = null
        email = ""
        senha = ""
        mensagemErro = null
    }
}