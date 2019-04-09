package Neko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
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
    return turnTimersIntoButtons(workTimers, "work");
  }

  public static List<JButton> getPlayTimeButtons() {
    return turnTimersIntoButtons(playTimers, "play");
  }

  public static List<JButton> getSnoozeTimeButtons() {
    return turnTimersIntoButtons(snoozeTimers, "snooze");
  }

  private static List<JButton> turnTimersIntoButtons(List<Integer> timers, String type) {
    List<JButton> buttons = new ArrayList<>();
    for (int time : timers) {
      JButton button = new JButton(time + " mins");
      button.setName(type + " " + time + " mins");
      button.addActionListener(new ButtonInteraction());
      buttons.add(button);
    }
    return buttons;
  }

  public static String getTypeFromButtonName(String buttonName) {
    Scanner sc = new Scanner(buttonName);
    String type = sc.next();
    sc.close();
    return type;
  }

  public static Duration getTimeFromButtonName(String buttonName) {
    Scanner sc = new Scanner(buttonName);
    sc.next();
    int minutes = sc.nextInt();
    sc.close();
    return Duration.ofMinutes(minutes);
  }

  private static class ButtonInteraction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      MattariNeko.flipNekoAwake();
      String timerDescription = ((JButton) actionEvent.getSource()).getName();
      String type = getTypeFromButtonName(timerDescription);
      Duration minutes = getTimeFromButtonName(timerDescription);
      boolean isSnooze = false;
      if (type.equals("snooze")) {
        isSnooze = true;
      }
      setUpTimer(minutes, isSnooze);
    }
  }

  private static void setUpTimer(Duration minutes, boolean isSnooze) {
    if (timer != null) {
      timer.cancel();
      timer.purge();
    }
    timer = new Timer("Neko Timer");
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        if (!isSnooze) {
          currentlyWorking = !currentlyWorking;
        }
        MattariNeko.flipNekoAwake();
      }
    }, minutes.toMillis());
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
