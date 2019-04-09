package FrontEnd;

import java.awt.*;
import javax.swing.*;

public class NekoPanel extends JPanel {

  private Image currentImage;

  public NekoPanel() {
    setBackground(new java.awt.Color(0, 0, 0, 0));
    setOpaque(false);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.clearRect(0,0, NekoImages.IMAGE_HEIGHT, NekoImages.IMAGE_WIDTH);
    g.drawImage(currentImage, 0, 0, this);
  }

  public void paintNeko(Image neko) {
    currentImage = neko;
    repaint();
  }

}
