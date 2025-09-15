# Curso DIO - Projetos Java

Este repositório contém projetos desenvolvidos durante o curso da DIO (Digital Innovation One).

## Projetos Incluídos

### 1. Sistema Contador
Localização: `src/sistemacontador/`

Sistema para análise e seleção de candidatos com funcionalidade de contador interativo.

**Como executar:**
```bash
cd src
javac sistemacontador/SistemaContador.java
java sistemacontador.SistemaContador
```

### 2. Gerador de Cartão de Visita em Japonês
Localização: `src/cartaovisita/`

Gerador de cartão de visita profissional em japonês para o usuário cassiodevpro.

**Informações do Cartão:**
- Nome: カッシオ (Cassio em katakana)
- Profissão: ソフトウェア開発者 (Desenvolvedor de software)
- Telefone: +55 61 99214-6651

**Como executar:**

1. **Versão básica:**
```bash
cd src
javac cartaovisita/CartaoVisita.java
java cartaovisita.CartaoVisita
```

2. **Versão com alta qualidade para PDF:**
```bash
cd src
javac cartaovisita/GeradorPDF.java
java cartaovisita.GeradorPDF
```

**Arquivos gerados:**
- `cartao_visita_cassio.png` - Imagem do cartão (visualização)
- `cartao_visita_cassio_print.png` - Versão de alta qualidade para impressão

**Para gerar PDF:**
- Use um conversor online PNG para PDF
- Imprima a imagem como PDF pelo sistema operacional
- Tamanho recomendado: 85.6 x 53.98 mm (padrão internacional)

**Características:**
- Layout profissional e centralizado
- Suporte a caracteres japoneses (katakana e kanji)
- Fontes tradicionais japonesas
- Nome em destaque
- Sem uso de kanji com direitos autorais
- Versão otimizada para impressão (300 DPI)

## Requisitos

- Java 17 ou superior
- Sistema operacional com suporte a fontes Unicode para caracteres japoneses

## Estrutura do Projeto

```
cursoDio/
├── src/
│   ├── sistemacontador/
│   │   └── SistemaContador.java
│   └── cartaovisita/
│       ├── CartaoVisita.java
│       └── GeradorPDF.java
├── README.md
└── .gitignore
```

## Contribuições

Este é um projeto educacional desenvolvido como parte do curso DIO.