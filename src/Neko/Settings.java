package Neko;


import FrontEnd.NekoFrame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Settings {

  File settings;

  public Settings() {
    String file = System.getProperty("user.home") + System.getProperty("file.separator") + "MattariNeko.conf";
    settings = new File(file);
    if (!settings.exists()) {
      Path copied = Paths.get(file);
      Path originalPath = Paths.get("MattariNeko.conf");
      try {
        Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
        loadSettings();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void loadSettings() throws FileNotFoundException {
    Scanner sc = new Scanner(settings);
    while (sc.hasNext()) {
      String line = sc.nextLine();
      if (line.charAt(0) =='#') {
        continue;
      }
      Scanner s = new Scanner(line);
      String option = s.next();
      if (option.equals("Work")) {
        List<Integer> timers = getTimers(s);
        TimersUtil.setWorkTimers(timers);
      } else if (option.equals("Play")) {
        List<Integer> timers = getTimers(s);
        TimersUtil.setPlayTimers(timers);
      } else if (option.equals("Snooze")) {
        List<Integer> timers = getTimers(s);
        TimersUtil.setSnoozeTimers(timers);
      } else if (option.equals("Screen")) {
        double widthPercent = sc.nextDouble();
        double heightPercent = sc.nextDouble();
        NekoFrame.setWidthPercent(widthPercent);
        NekoFrame.setHeightPercent(heightPercent);
      } else if (option.equals("Speed")) {
        int movement = sc.nextInt();
        NekoMovementCoordinator.setMaxMovementPerAction(movement);
      }
      s.close();
    }
    sc.close();
  }

  private List<Integer> getTimers(Scanner s) {
    List<Integer> timers = new ArrayList<>();
    while (s.hasNextInt()) {
      timers.add(s.nextInt());
    }
    return timers;
  }


}
