package cartaovisita;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

/**
 * Visualizador simples para mostrar os cartões gerados
 */
public class ImageViewer {
    public static void main(String[] args) {
        try {
            // Carregar a imagem do cartão
            BufferedImage image = ImageIO.read(new File("cartao_visita_cassio.png"));
            
            // Criar janela
            JFrame frame = new JFrame("Cartão de Visita - カッシオ (Cassio)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Criar painel com a imagem
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    
                    // Calcular escala para caber na tela
                    int panelWidth = getWidth();
                    int panelHeight = getHeight();
                    double scaleX = (double) panelWidth / image.getWidth();
                    double scaleY = (double) panelHeight / image.getHeight();
                    double scale = Math.min(scaleX, scaleY);
                    
                    int scaledWidth = (int) (image.getWidth() * scale);
                    int scaledHeight = (int) (image.getHeight() * scale);
                    
                    int x = (panelWidth - scaledWidth) / 2;
                    int y = (panelHeight - scaledHeight) / 2;
                    
                    g2d.drawImage(image, x, y, scaledWidth, scaledHeight, null);
                }
            };
            
            panel.setPreferredSize(new Dimension(650, 410));
            panel.setBackground(Color.LIGHT_GRAY);
            
            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
            System.out.println("Visualizador aberto com o cartão de visita!");
            System.out.println("Feche a janela para encerrar.");
            
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem: " + e.getMessage());
            System.err.println("Certifique-se de que o arquivo 'cartao_visita_cassio.png' existe.");
        }
    }
}