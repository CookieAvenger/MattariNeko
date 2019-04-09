package FrontEnd;

import FrontEnd.NekoActions.NekoImages;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class NekoFrame extends JFrame {

  private NekoPanel panel = new NekoPanel();
  private static MattariFrame relaxFrame = null;
  private static int x, y;
  private static double widthPercent = 97.5, heightPercent = 97.5;

  public NekoFrame() {
    setUndecorated(true);
    setBounds(new Rectangle(NekoImages.IMAGE_WIDTH, NekoImages.IMAGE_HEIGHT));
    setBackground(new java.awt.Color(0, 0, 0, 0));
    setNekoLocation(getStartingLocation());
    setSize(NekoImages.IMAGE_WIDTH, NekoImages.IMAGE_HEIGHT);
    setResizable(false);
    setAlwaysOnTop(true);
    addMouseListener(new MouseInteraction());

    Container c = getContentPane();
    c.add(panel);
  }

  public Point getValidNekoLocation(Point location) {
    Dimension screenSize = getScreenSize();
    x = location.x - (NekoImages.IMAGE_WIDTH / 2);
    y = location.y - (NekoImages.IMAGE_HEIGHT / 2);
    if (x < 0) {
      x = 0;
    } else if (x > screenSize.width - NekoImages.IMAGE_HEIGHT) {
      x = screenSize.width - NekoImages.IMAGE_HEIGHT;
    }
    if (y < 0) {
      y = 0;
    } else if (y > screenSize.height - NekoImages.IMAGE_WIDTH) {
      y = screenSize.height - NekoImages.IMAGE_WIDTH;
    }
    return new Point(x, y);
  }

  public void setNekoLocation(Point location) {
    setLocation(getValidNekoLocation(location));
  }

  public Point getNekoLocation() {
    return new Point(x + (NekoImages.IMAGE_WIDTH / 2), (y + NekoImages.IMAGE_HEIGHT / 2));
  }

  public Point getMouseLocation() throws Exception {
    Point location = MouseInfo.getPointerInfo().getLocation();
    if (location == null) {
      throw new Exception("Mouse location not retrieved");
    }
    return location;
  }

  public void paintNeko(Image neko) {
    panel.paintNeko(neko);
  }

  public Point getStartingLocation() {
    Dimension screenSize = getScreenSize();
    int x = (int) Math.round(screenSize.getWidth() * (widthPercent/100));
    int y = (int) Math.round(screenSize.getHeight() * (heightPercent/100));
    return new Point(x, y);
  }

  public Dimension getScreenSize() {
    return Toolkit.getDefaultToolkit().getScreenSize();
  }


  private static class MouseInteraction implements MouseListener {

    public MouseInteraction() {
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
      //open relax frame and make sure it's on top of cat, when it's done, let the cat go back
      NekoFrame.openMattariFrame();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
  }

  public static void openMattariFrame() {
    if (relaxFrame != null) {
      return;
    }
    relaxFrame = new MattariFrame(new Point(x, y));
    relaxFrame.setVisible(true);
  }

  public static void closeMattariFrame() {
    relaxFrame = null;
  }

  public static void setWidthPercent(double percent) {
    widthPercent = percent;
  }

  public static void setHeightPercent(double percent) {
    heightPercent = percent;
  }
}
