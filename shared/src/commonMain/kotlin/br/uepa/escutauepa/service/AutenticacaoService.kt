package br.uepa.escutauepa.service

import br.uepa.escutauepa.model.Usuario

/* Implementa os serviços de autenticação a partir de um usuário de demonstração.
São utilizadas técnicas de encapsulamento para impedir que a sessão do usuário
seja alterada, tornando possível apenas consultar suas informações de sessão */
class AutenticacaoService {

    private val usuarioDemonstracao = Usuario(
        id = "usuario1",
        nome = "Aluno UEPA",
        email = "aluno@uepa.br"
    )

    private val senhaDemonstracao = "escuta123"

    var usuarioAtual: Usuario? = null
        private set

    fun autenticar(
        email: String,
        senha: String
    ): Usuario? {

        val emailValido =
            email.trim().equals(
                usuarioDemonstracao.email,
                ignoreCase = true
            )

        val senhaValida =
            senha == senhaDemonstracao

        return if (emailValido && senhaValida) {
            usuarioAtual = usuarioDemonstracao
            usuarioAtual
        } else {
            usuarioAtual = null
            null
        }
    }

    fun estaAutenticado(): Boolean {
        return usuarioAtual != null
    }

    fun encerrarSessao() {
        usuarioAtual = null
    }
}
