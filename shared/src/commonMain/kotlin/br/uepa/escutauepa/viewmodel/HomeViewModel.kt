package br.uepa.escutauepa.viewmodel

import br.uepa.escutauepa.model.Usuario
import br.uepa.escutauepa.service.AutenticacaoService

class HomeViewModel(
    private val autenticacaoService: AutenticacaoService
) {
    val usuario: Usuario?
        get() = autenticacaoService.usuarioAtual

    fun obterNomeUsuario(): String {
        return usuario?.nome ?: "Usuário"
    }

    fun sair() {
        autenticacaoService.encerrarSessao()
    }
}