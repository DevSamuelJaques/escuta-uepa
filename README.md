# EscutaUEPA

O **EscutaUEPA** é um aplicativo acadêmico desenvolvido em **Kotlin e Compose Multiplatform** para a disciplina de **Programação Orientada a Objetos (POO)**. A proposta é permitir que membros da comunidade acadêmica da UEPA registrem e consultem manifestações.

> **Projeto demonstrativo:** o login é fictício e os dados são guardados apenas em memória.

## Links importantes

- [EscutaUEPA (Acesse aqui)](https://fantastic-frangipane-dcab45.netlify.app/)
- [Pasta principal do projeto](https://github.com/DevSamuelJaques/escuta-uepa/tree/main/shared/src/commonMain/kotlin/br/uepa/escutauepa)

## Funcionalidades

- **Login:** acesso demonstrativo por e-mail e senha.
- **Tela inicial:** acesso às principais funções.
- **Nova manifestação:** registro de **sugestão, reclamação ou denúncia**, direcionada a **aluno, professor ou campus**, com título e descrição.
- **Histórico:** consulta das manifestações cadastradas e de seus status.

## Estrutura de pacotes

Os arquivos principais ficam em `shared/src/commonMain/kotlin/br/uepa/escutauepa`:

```text
br/uepa/escutauepa/
├── App.kt                       # Organiza as telas do aplicativo
├── model/                       # Classes que representam os dados
│   ├── Usuario.kt
│   ├── Manifestacao.kt
│   ├── Sugestao.kt
│   ├── Reclamacao.kt
│   ├── Denuncia.kt
│   ├── ConteudoManifestacao.kt
│   ├── ControleStatus.kt
│   ├── Destinatario.kt
│   ├── StatusManifestacao.kt
│   └── ResumoManifestacao.kt
├── repository/                  # Armazena e consulta manifestações
│   ├── ManifestacaoRepository.kt
│   └── InMemoryManifestacaoRepository.kt
├── service/                     # Regras de cadastro e login
│   ├── ManifestacaoService.kt
│   └── AutenticacaoService.kt
├── viewmodel/                   # Liga as telas à lógica do aplicativo
│   ├── LoginViewModel.kt
│   ├── HomeViewModel.kt
│   ├── ManifestacaoViewModel.kt
│   └── HistoricoViewModel.kt
└── ui/                  # Telas feitas com Compose
    ├── LoginScreen.kt
    ├── HomeScreen.kt
    ├── ManifestacaoScreen.kt
    └── HistoricoScreen.kt
```

## Onde usamos POO?

| Conceito | Aplicação no EscutaUEPA |
|---|---|
| **Classes** | `Usuario`, `Manifestacao` e outras classes definem dados e comportamentos. |
| **Objetos** | Cada sugestão, reclamação ou denúncia cadastrada é um objeto. |
| **Encapsulamento** | Atributos privados e métodos controlam o acesso aos dados, como em `AutenticacaoService`. |
| **Herança** | `Sugestao`, `Reclamacao` e `Denuncia` herdam de `Manifestacao`. |
| **Polimorfismo** | Cada tipo de manifestação pode executar sua própria versão de `validar()`. |
| **Composição** | `Manifestacao` contém objetos como `ConteudoManifestacao` e `ControleStatus`. |
| **Organização em pacotes** | Os arquivos são separados em `model`, `repository`, `service`, `viewmodel` e `ui`. |

## Organização do projeto

As atividades são acompanhadas no **Taiga**, e o código é versionado com **Git e GitHub**, seguindo a política de commits da disciplina.