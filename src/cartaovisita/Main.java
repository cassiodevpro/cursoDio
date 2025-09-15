package cartaovisita;

import java.util.Scanner;

/**
 * Classe principal para demonstrar o gerador de cartão de visita japonês
 * Oferece opções interativas para o usuário
 */
public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=================================");
        System.out.println("GERADOR DE CARTÃO DE VISITA JAPONÊS");
        System.out.println("=================================");
        System.out.println();
        System.out.println("Cartão para: cassiodevpro");
        System.out.println("Nome: カッシオ (Cassio)");
        System.out.println("Profissão: ソフトウェア開発者 (Desenvolvedor de Software)");
        System.out.println("Telefone: +55 61 99214-6651");
        System.out.println();
        
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Gerar cartão básico (PNG)");
            System.out.println("2 - Gerar cartão para impressão (Alta qualidade)");
            System.out.println("3 - Gerar ambas as versões");
            System.out.println("4 - Informações sobre o cartão");
            System.out.println("0 - Sair");
            System.out.println();
            System.out.print("Digite sua opção: ");
            
            try {
                int opcao = scanner.nextInt();
                System.out.println();
                
                switch (opcao) {
                    case 1:
                        gerarCartaoBasico();
                        break;
                    case 2:
                        gerarCartaoImpressao();
                        break;
                    case 3:
                        gerarAmbasVersoes();
                        break;
                    case 4:
                        mostrarInformacoes();
                        break;
                    case 0:
                        System.out.println("Encerrando o programa...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
                
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                scanner.nextLine(); // Limpar buffer
            }
            
            System.out.println();
            System.out.println("Pressione Enter para continuar...");
            scanner.nextLine();
            scanner.nextLine();
            System.out.println();
        }
    }
    
    private static void gerarCartaoBasico() {
        try {
            System.out.println("Gerando cartão básico...");
            CartaoVisita cartao = new CartaoVisita();
            cartao.gerarCartaoImagem();
            System.out.println("✓ Cartão básico gerado: cartao_visita_cassio.png");
            System.out.println("  Tamanho: 325x205 pixels (visualização)");
        } catch (Exception e) {
            System.out.println("✗ Erro ao gerar cartão básico: " + e.getMessage());
        }
    }
    
    private static void gerarCartaoImpressao() {
        try {
            System.out.println("Gerando cartão para impressão...");
            GeradorPDF gerador = new GeradorPDF();
            gerador.gerarCartaoPDFSimples();
            System.out.println("✓ Cartão para impressão gerado!");
            System.out.println("  Arquivos:");
            System.out.println("  - cartao_visita_cassio.png (visualização)");
            System.out.println("  - cartao_visita_cassio_print.png (impressão 300 DPI)");
        } catch (Exception e) {
            System.out.println("✗ Erro ao gerar cartão para impressão: " + e.getMessage());
        }
    }
    
    private static void gerarAmbasVersoes() {
        System.out.println("Gerando todas as versões do cartão...");
        gerarCartaoBasico();
        System.out.println();
        gerarCartaoImpressao();
    }
    
    private static void mostrarInformacoes() {
        System.out.println("INFORMAÇÕES SOBRE O CARTÃO DE VISITA");
        System.out.println("=====================================");
        System.out.println();
        System.out.println("Conteúdo do cartão:");
        System.out.println("• Nome em japonês: カッシオ (katakana)");
        System.out.println("• Nome romanizado: Cassio");
        System.out.println("• Profissão em japonês: ソフトウェア開発者");
        System.out.println("  - ソフトウェア = Software (katakana)");
        System.out.println("  - 開発者 = Desenvolvedor (kanji)");
        System.out.println("• Profissão em português: Desenvolvedor de Software");
        System.out.println("• Telefone: +55 61 99214-6651");
        System.out.println();
        System.out.println("Especificações técnicas:");
        System.out.println("• Tamanho padrão: 85.6 x 53.98 mm");
        System.out.println("• Versão básica: 325 x 205 pixels");
        System.out.println("• Versão impressão: 1012 x 638 pixels (300 DPI)");
        System.out.println("• Formato de saída: PNG");
        System.out.println("• Fontes: SansSerif e Dialog (suporte Unicode)");
        System.out.println();
        System.out.println("Para converter para PDF:");
        System.out.println("1. Use um conversor online PNG → PDF");
        System.out.println("2. Imprima como PDF pelo sistema operacional");
        System.out.println("3. Mantenha o tamanho 85.6 x 53.98 mm");
    }
}