package br.uepa.escutauepa.model

/* Implementa o encapsulamento no controle do status de
uma manifestação. Outras classes podem consultar o status da manifestação,
mas apenas esta classe pode alterá-los */
class ControleStatus {
    var status = StatusManifestacao.PENDENTE
        private set

    fun iniciarAnalise() {
        // Caso o status atual da manifestação não seja PENDENTE, uma exceção é lançada,
        // pois não é possível iniciar uma análise sem que o status da manifestação seja PENDENTE
        require(status == StatusManifestacao.PENDENTE) {
            "A manifestação não está pendente."
        }

        status = StatusManifestacao.EM_ANALISE
    }

    fun concluir() {
        // Caso o status atual da manifestação não seja EM_ANALISE, uma exceção é lançada,
        // pois não é possível iniciar uma análise sem que o status da manifestação seja EM_ANALISE
        require(status == StatusManifestacao.EM_ANALISE) {
            "A manifestação não está em análise."
        }

        status = StatusManifestacao.CONCLUIDA
    }
}