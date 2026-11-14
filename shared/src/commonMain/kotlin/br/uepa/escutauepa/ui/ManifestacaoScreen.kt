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
import br.uepa.escutauepa.model.Destinatario
import br.uepa.escutauepa.viewmodel.*

private val VerdeUEPA = Color(0xFF007F77)

@Composable
fun ManifestacaoScreen(
    viewModel: ManifestacaoViewModel,
    autorId: String,
    onVoltar: () -> Unit,
    onCadastroRealizado: () -> Unit
) {
    if (viewModel.cadastroRealizado) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text("Manifestação cadastrada!") },
            text = {
                Text("Seu registro foi salvo com sucesso.")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.limparFormulario()
                        onCadastroRealizado()
                    }
                ) {
                    Text("Continuar")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        TextButton(onClick = onVoltar) {
            Text("← Voltar")
        }

        Text(
            text = "Nova manifestação",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Preencha os dados abaixo.",
            color = Color.Gray
        )

        Text(
            text = "NATUREZA",
            fontWeight = FontWeight.Bold,
            color = VerdeUEPA
        )

        NaturezaManifestacao.entries.forEach { natureza ->
            OpcaoSelecionavel(
                titulo = when (natureza) {
                    NaturezaManifestacao.SUGESTAO -> "Sugestão"
                    NaturezaManifestacao.RECLAMACAO -> "Reclamação"
                    NaturezaManifestacao.DENUNCIA -> "Denúncia"
                },
                selecionado = viewModel.natureza == natureza,
                onClick = {
                    viewModel.selecionarNatureza(natureza)
                }
            )
        }

        Text(
            text = "DESTINATÁRIO",
            fontWeight = FontWeight.Bold,
            color = VerdeUEPA
        )

        Destinatario.entries.forEach { destinatario ->
            OpcaoSelecionavel(
                titulo = when (destinatario) {
                    Destinatario.CAMPUS -> "Campus"
                    Destinatario.PROFESSOR -> "Professor"
                    Destinatario.ALUNO -> "Aluno"
                },
                selecionado =
                    viewModel.destinatario == destinatario,
                onClick = {
                    viewModel.selecionarDestinatario(destinatario)
                }
            )
        }

        Text(
            text = "SUA MENSAGEM",
            fontWeight = FontWeight.Bold,
            color = VerdeUEPA
        )

        OutlinedTextField(
            value = viewModel.titulo,
            onValueChange = viewModel::atualizarTitulo,
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(14.dp)
        )

        OutlinedTextField(
            value = viewModel.descricao,
            onValueChange = viewModel::atualizarDescricao,
            label = { Text("Descrição") },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 150.dp),
            minLines = 5,
            shape = RoundedCornerShape(14.dp)
        )

        viewModel.mensagemErro?.let { erro ->
            Text(
                text = erro,
                color = MaterialTheme.colorScheme.error
            )
        }

        Button(
            onClick = {
                viewModel.cadastrar(autorId)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = VerdeUEPA
            ),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text("Enviar manifestação")
        }
    }
}

@Composable
private fun OpcaoSelecionavel(
    titulo: String,
    selecionado: Boolean,
    onClick: () -> Unit
) {
    val cor = if (selecionado) {
        Color(0xFFE0F5F1)
    } else {
        Color.White
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = cor
        ),
        border = if (selecionado) {
            androidx.compose.foundation.BorderStroke(
                2.dp, VerdeUEPA
            )
        } else {
            null
        },
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = titulo,
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Medium
            )

            RadioButton(
                selected = selecionado,
                onClick = onClick
            )
        }
    }
}