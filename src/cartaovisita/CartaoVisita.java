package cartaovisita;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.print.*;

/**
 * Gerador de Cartão de Visita em Japonês
 * Cria um cartão de visita profissional para cassiodevpro com informações em japonês
 */
public class CartaoVisita implements Printable {
    
    // Informações do cartão
    private static final String NOME_JAPONES = "カッシオ";
    private static final String NOME_ROMANJI = "Cassio";
    private static final String PROFISSAO = "ソフトウェア開発者";
    private static final String PROFISSAO_PT = "Desenvolvedor de Software";
    private static final String TELEFONE = "+55 61 99214-6651";
    
    // Dimensões do cartão (padrão internacional: 85.6 x 53.98 mm)
    private static final int CARD_WIDTH = 325;  // ~85.6mm em pixels
    private static final int CARD_HEIGHT = 205; // ~53.98mm em pixels
    
    public static void main(String[] args) {
        try {
            CartaoVisita cartao = new CartaoVisita();
            cartao.gerarCartaoPDF();
            cartao.gerarCartaoImagem();
            System.out.println("Cartão de visita gerado com sucesso!");
            System.out.println("Arquivos criados:");
            System.out.println("- cartao_visita_cassio.png (imagem)");
            System.out.println("- Use a função de impressão do sistema para gerar PDF");
        } catch (Exception e) {
            System.err.println("Erro ao gerar cartão de visita: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Gera o cartão como imagem PNG
     */
    public void gerarCartaoImagem() throws IOException {
        BufferedImage image = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        
        // Configurações de renderização para melhor qualidade
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        desenharCartao(g2d, CARD_WIDTH, CARD_HEIGHT);
        g2d.dispose();
        
        // Salvar como PNG
        File outputFile = new File("cartao_visita_cassio.png");
        ImageIO.write(image, "png", outputFile);
    }
    
    /**
     * Configura a impressão para PDF (usando o sistema de impressão do Java)
     */
    public void gerarCartaoPDF() throws PrinterException {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        
        // Configurar formato do papel
        PageFormat pageFormat = job.defaultPage();
        Paper paper = new Paper();
        
        // Definir margens e tamanho
        double margin = 18; // ~5mm de margem
        paper.setImageableArea(margin, margin, 
                              CARD_WIDTH + margin, 
                              CARD_HEIGHT + margin);
        paper.setSize(CARD_WIDTH + (margin * 2), CARD_HEIGHT + (margin * 2));
        pageFormat.setPaper(paper);
        
        System.out.println("Configuração de impressão preparada.");
        System.out.println("Para gerar PDF, use um driver de impressão PDF ou");
        System.out.println("imprima para arquivo PDF usando o sistema operacional.");
        
        // Nota: Para gerar PDF automaticamente, seria necessário usar bibliotecas externas
        // como iText ou Apache PDFBox, mas mantendo a solução simples com bibliotecas padrão
    }
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }
        
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        
        desenharCartao(g2d, (int) pageFormat.getImageableWidth(), (int) pageFormat.getImageableHeight());
        
        return PAGE_EXISTS;
    }
    
    /**
     * Desenha o layout do cartão de visita
     */
    private void desenharCartao(Graphics2D g2d, int width, int height) {
        // Fundo branco
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        
        // Borda elegante
        g2d.setColor(new Color(200, 200, 200));
        g2d.setStroke(new BasicStroke(1));
        g2d.drawRect(5, 5, width - 10, height - 10);
        
        // Cor do texto principal
        g2d.setColor(Color.BLACK);
        
        // Configurar fontes
        Font fonteTitulo = new Font("SansSerif", Font.BOLD, 24);
        Font fonteSubtitulo = new Font("SansSerif", Font.PLAIN, 16);
        Font fonteTexto = new Font("SansSerif", Font.PLAIN, 14);
        Font fonteJapones = new Font("Dialog", Font.BOLD, 20); // Dialog geralmente suporta caracteres japoneses
        
        // Centralizar texto
        FontMetrics fm;
        int x, y;
        
        // Nome em japonês (destaque)
        g2d.setFont(fonteJapones);
        fm = g2d.getFontMetrics();
        x = (width - fm.stringWidth(NOME_JAPONES)) / 2;
        y = 40;
        g2d.drawString(NOME_JAPONES, x, y);
        
        // Nome em romanji (menor)
        g2d.setFont(fonteSubtitulo);
        fm = g2d.getFontMetrics();
        x = (width - fm.stringWidth(NOME_ROMANJI)) / 2;
        y += 25;
        g2d.drawString(NOME_ROMANJI, x, y);
        
        // Linha separadora
        g2d.setColor(new Color(180, 180, 180));
        g2d.drawLine(30, y + 10, width - 30, y + 10);
        g2d.setColor(Color.BLACK);
        
        // Profissão em japonês
        g2d.setFont(fonteJapones);
        fm = g2d.getFontMetrics();
        x = (width - fm.stringWidth(PROFISSAO)) / 2;
        y += 35;
        g2d.drawString(PROFISSAO, x, y);
        
        // Profissão em português
        g2d.setFont(fonteTexto);
        fm = g2d.getFontMetrics();
        x = (width - fm.stringWidth(PROFISSAO_PT)) / 2;
        y += 20;
        g2d.drawString(PROFISSAO_PT, x, y);
        
        // Telefone
        g2d.setFont(fonteTexto);
        fm = g2d.getFontMetrics();
        x = (width - fm.stringWidth(TELEFONE)) / 2;
        y += 30;
        g2d.drawString(TELEFONE, x, y);
        
        // Adicionar um toque elegante - pequenos detalhes decorativos
        g2d.setColor(new Color(100, 100, 100));
        g2d.fillOval(width / 2 - 2, height - 20, 4, 4);
    }
}