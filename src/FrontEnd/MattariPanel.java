package FrontEnd;

import static FrontEnd.NekoFrame.closeMattariFrame;

import Neko.MattariNeko;
import Neko.TimersUtil;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MattariPanel extends JPanel {

  static private MattariFrame MattariFrame;

  public MattariPanel(MattariFrame frame) {
    MattariFrame = frame;
    setBackground(Color.white);

    int rows = 4;
    if (TimersUtil.isAtWork()) {
      rows += TimersUtil.getPlayTimeButtons().size();
    } else {
      rows += TimersUtil.getWorkTimeButtons().size();
    }
    rows += TimersUtil.getSnoozeTimeButtons().size();

    setLayout(new GridLayout(rows, 0));

    JLabel initialQuestion = new JLabel();
    if (TimersUtil.isAtWork()) {
      initialQuestion.setText("How long would you like to play for?");
    } else {
      initialQuestion.setText("How long would you like to work for?");
    }
    add(initialQuestion);
    if (TimersUtil.isAtWork()) {
      addListOfButtons(TimersUtil.getPlayTimeButtons());
    } else {
      addListOfButtons(TimersUtil.getWorkTimeButtons());
    }

    JLabel snoozeQuestion = new JLabel("Snooze kitty, come back in: ");
    add(snoozeQuestion);
    addListOfButtons(TimersUtil.getSnoozeTimeButtons());

    JLabel endPrompt = new JLabel("Good night!");
    add(endPrompt);
    add(getExitButton());
  }

  private void addListOfButtons(List<JButton> buttons) {
    for (JButton button : buttons) {
      button.addActionListener(new TimerInteraction());
      add(button);
    }
  }

  private JButton getExitButton() {
    JButton exitButton = new JButton("Sweet Dreams!");
    exitButton.addActionListener(new ExitInteration());
    return exitButton;
  }

  private static class TimerInteraction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      closeMattariFrame();
      MattariFrame.dispose();
    }
  }

  private static class ExitInteration implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      MattariNeko.nekoExit();
      System.exit(0);
    }
  }
}
