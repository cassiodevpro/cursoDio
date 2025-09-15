package cartaovisita;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 * Gerador de PDF simples para o cartão de visita
 * Converte a imagem do cartão para um formato que pode ser facilmente convertido em PDF
 */
public class GeradorPDF {
    
    public static void main(String[] args) {
        try {
            GeradorPDF gerador = new GeradorPDF();
            gerador.gerarCartaoPDFSimples();
            System.out.println("Cartão de visita gerado com sucesso!");
            System.out.println("\nArquivos gerados:");
            System.out.println("1. cartao_visita_cassio.png - Imagem do cartão");
            System.out.println("2. cartao_visita_cassio_print.png - Versão para impressão");
            System.out.println("\nPara converter para PDF:");
            System.out.println("- Use qualquer conversor online PNG para PDF");
            System.out.println("- Ou imprima a imagem como PDF usando seu sistema operacional");
            System.out.println("- Tamanho recomendado: 85.6 x 53.98 mm (padrão cartão de visita)");
        } catch (Exception e) {
            System.err.println("Erro ao gerar cartão: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Gera uma versão otimizada para impressão e conversão PDF
     */
    public void gerarCartaoPDFSimples() throws IOException {
        // Dimensões para impressão de alta qualidade (300 DPI)
        int printWidth = 1012;  // 85.6mm * 300 DPI / 25.4
        int printHeight = 638;  // 53.98mm * 300 DPI / 25.4
        
        // Criar imagem em alta resolução
        BufferedImage imagemPrint = new BufferedImage(printWidth, printHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = imagemPrint.createGraphics();
        
        // Configurações para alta qualidade
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        desenharCartaoAltaQualidade(g2d, printWidth, printHeight);
        g2d.dispose();
        
        // Salvar versão para impressão
        File printFile = new File("cartao_visita_cassio_print.png");
        ImageIO.write(imagemPrint, "png", printFile);
        
        // Também gerar a versão normal
        CartaoVisita cartaoNormal = new CartaoVisita();
        cartaoNormal.gerarCartaoImagem();
    }
    
    /**
     * Desenha o cartão em alta qualidade para impressão
     */
    private void desenharCartaoAltaQualidade(Graphics2D g2d, int width, int height) {
        // Fundo branco
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        
        // Borda elegante mais espessa para impressão
        g2d.setColor(new Color(200, 200, 200));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(15, 15, width - 30, height - 30);
        
        // Cor do texto
        g2d.setColor(Color.BLACK);
        
        // Fontes maiores para impressão
        Font fonteTituloJp = new Font("Dialog", Font.BOLD, 64);
        Font fonteSubtitulo = new Font("SansSerif", Font.PLAIN, 40);
        Font fonteTexto = new Font("SansSerif", Font.PLAIN, 36);
        Font fonteJapones = new Font("Dialog", Font.BOLD, 48);
        
        // Informações do cartão
        String nomeJapones = "カッシオ";
        String nomeRomanji = "Cassio";
        String profissao = "ソフトウェア開発者";
        String profissaoPt = "Desenvolvedor de Software";
        String telefone = "+55 61 99214-6651";
        
        FontMetrics fm;
        int x, y;
        
        // Nome em japonês (destaque principal)
        g2d.setFont(fonteTituloJp);
        fm = g2d.getFontMetrics();
        x = (width - fm.stringWidth(nomeJapones)) / 2;
        y = 120;
        g2d.drawString(nomeJapones, x, y);
        
        // Nome em romanji
        g2d.setFont(fonteSubtitulo);
        fm = g2d.getFontMetrics();
        x = (width - fm.stringWidth(nomeRomanji)) / 2;
        y += 60;
        g2d.drawString(nomeRomanji, x, y);
        
        // Linha separadora
        g2d.setColor(new Color(180, 180, 180));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(100, y + 25, width - 100, y + 25);
        g2d.setColor(Color.BLACK);
        
        // Profissão em japonês
        g2d.setFont(fonteJapones);
        fm = g2d.getFontMetrics();
        x = (width - fm.stringWidth(profissao)) / 2;
        y += 85;
        g2d.drawString(profissao, x, y);
        
        // Profissão em português
        g2d.setFont(fonteTexto);
        fm = g2d.getFontMetrics();
        x = (width - fm.stringWidth(profissaoPt)) / 2;
        y += 50;
        g2d.drawString(profissaoPt, x, y);
        
        // Telefone
        g2d.setFont(fonteTexto);
        fm = g2d.getFontMetrics();
        x = (width - fm.stringWidth(telefone)) / 2;
        y += 70;
        g2d.drawString(telefone, x, y);
        
        // Detalhe decorativo
        g2d.setColor(new Color(100, 100, 100));
        g2d.fillOval(width / 2 - 6, height - 60, 12, 12);
    }
}