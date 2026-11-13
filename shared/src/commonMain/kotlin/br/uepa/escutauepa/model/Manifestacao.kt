package br.uepa.escutauepa.model

// Tipo de classe que permite herança e não pode ser instanciada para evitar instâncias genéricas
// A composição aqui é aplicada por meio das classes ConteudoManifestacao e ControleStatus
abstract class Manifestacao(
    val id: String,
    val autorId: String,
    val conteudo: ConteudoManifestacao,
    val destinatario: Destinatario,
    val dataCriacao: String
) {
    val controleStatus = ControleStatus()

    // A função não possui implementação aqui, mas obriga todas as subclasses a implementá-la
    abstract fun obterNatureza(): String

    // A função possui implementação padrão, as subclasses podem ou não utilizá-la
    open fun validar(): Boolean {
        return conteudo.titulo.isNotBlank() &&
                conteudo.descricao.isNotBlank()
    }
}