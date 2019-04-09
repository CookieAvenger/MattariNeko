package Neko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;

public class TimersUtil {

  private static List<Integer> workTimers = new ArrayList<>(Arrays.asList(90, 60, 30));
  private static List<Integer> playTimers = new ArrayList<>(Arrays.asList(15, 10, 5));
  private static List<Integer> snoozeTimers = new ArrayList<>(Arrays.asList(10, 5));
  private static Timer timer = null;
  private static boolean currentlyWorking = false;

  public static List<JButton> getWorkTimeButtons() {
    return turnTimersIntoButtons(workTimers, false);
  }

  public static List<JButton> getPlayTimeButtons() {
    return turnTimersIntoButtons(playTimers, false);
  }

  public static List<JButton> getSnoozeTimeButtons() {
    return turnTimersIntoButtons(snoozeTimers, true);
  }

  private static List<JButton> turnTimersIntoButtons(List<Integer> timers, boolean snoozeButtons) {
    List<JButton> buttons = new ArrayList<>();
    for (int time : timers) {
      String buttonText = Integer.toString(time);
      if (!snoozeButtons) {
        buttonText += " mins";
      }
      JButton button = new JButton(buttonText);
      button.addActionListener(new ButtonInteraction());
      buttons.add(button);
    }
    return buttons;
  }

  private static class ButtonInteraction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      MattariNeko.flipNeko();
      setUpTimer(((JButton) actionEvent.getSource()).getText());
    }
  }

  private static void setUpTimer(String text) {
    boolean isSnooze = true;
    Scanner sc = new Scanner(text);
    int minutes = sc.nextInt();
    if (sc.hasNext()) {
      isSnooze = false;
    }
    sc.close();
    if (timer != null) {
      timer.cancel();
      timer.purge();
    }
    timer = new Timer("Neko Timer");
    boolean finalIsSnooze = isSnooze;
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        if (!finalIsSnooze) {
          currentlyWorking = !currentlyWorking;
        }
        MattariNeko.flipNeko();
      }
    }, minutes * 60 * 1000);
  }

  public static boolean isAtWork() {
    return currentlyWorking;
  }

  public static void setWorkTimers(List<Integer> timers) {
    workTimers = timers;
  }

  public static void setPlayTimers(List<Integer> timers) {
    playTimers = timers;
  }

  public static void setSnoozeTimers(List<Integer> snoozeTimers) {
    TimersUtil.snoozeTimers = snoozeTimers;
  }
}
