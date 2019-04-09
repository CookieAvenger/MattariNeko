package FrontEnd;

import static FrontEnd.NekoFrame.closeMattariFrame;
import static Neko.MattariNeko.exitNeko;

import Neko.ActivityTracker;
import Neko.TimersUtil;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MattariPanel extends JPanel {

  static private MattariFrame MattariFrame;
  static private boolean firstTime = true;
  static private JTextField optionalDescription = null;

  public MattariPanel(MattariFrame frame) {
    MattariFrame = frame;
    setBackground(Color.white);

    setLayout(new GridLayout(calculateRowsInPanel(), 0));

    if (!firstTime) {
      JLabel descriptionQuestion = new JLabel("(Optional) What did you just do?");
      add(descriptionQuestion);
      optionalDescription = new JTextField();
      add(optionalDescription);
    }

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
    if (!firstTime) {
      JLabel snoozeQuestion = new JLabel("Snooze kitty, come back in: ");
      add(snoozeQuestion);
      addListOfButtons(TimersUtil.getSnoozeTimeButtons());
    }

    JLabel endPrompt = new JLabel("Good night!");
    add(endPrompt);
    add(getExitButton());

    firstTime = false;
  }

  public static void recordEndOfActivity() {
    if (optionalDescription != null) {
      String description = optionalDescription.getText();
      ActivityTracker.ActivityFinish(description);
    }
    optionalDescription = null;
  }

  private int calculateRowsInPanel() {
    int rows = 3;
    if (!firstTime) {
      rows += 2;
      rows += TimersUtil.getSnoozeTimeButtons().size() + 1;
    }
    if (TimersUtil.isAtWork()) {
      rows += TimersUtil.getPlayTimeButtons().size();
    } else {
      rows += TimersUtil.getWorkTimeButtons().size();
    }
    return rows;
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
      String timerDescription = ((JButton) actionEvent.getSource()).getName();
      String type = TimersUtil.getTypeFromButtonName(timerDescription);
      Duration time = TimersUtil.getTimeFromButtonName(timerDescription);
      if (!"snooze".equals(type.toLowerCase())) {
        MattariPanel.recordEndOfActivity();
        ActivityTracker.ActivityBegun(type, time);
      }
      closeMattariFrame();
      MattariFrame.dispose();
    }
  }

  private static class ExitInteration implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      MattariPanel.recordEndOfActivity();
      exitNeko();
      closeMattariFrame();
      MattariFrame.dispose();
    }
  }
}
