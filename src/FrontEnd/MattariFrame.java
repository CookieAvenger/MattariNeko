package FrontEnd;

import static FrontEnd.NekoFrame.closeMattariFrame;

import Neko.TimersUtil;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class MattariFrame extends JFrame {

  private MattariPanel panel;
  public MattariFrame(Point location) {
    panel = new MattariPanel(this);
    if (!TimersUtil.isAtWork()) {
      setTitle("Work Timer");
    } else {
      setTitle("Play Timer");
    }
    setLocation(location);
    addWindowListener(new WindowAdapter( ) {
      public void windowClosing(WindowEvent e) {
        closeMattariFrame();
      }
    });


    Container c = getContentPane();
    c.add(panel);
    pack();
  }

}
