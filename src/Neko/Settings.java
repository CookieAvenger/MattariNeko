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
import java.util.Locale;
import java.util.Scanner;

public class Settings {

  final static File SETTINGS = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "MattariNeko.conf");

  public Settings() {
    if (!SETTINGS.exists()) {
      Path copied = Paths.get(SETTINGS.getPath());
      Path originalPath = Paths.get("MattariNeko.conf");
      try {
        Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try {
      loadSettings();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private void loadSettings() throws FileNotFoundException {
    Scanner sc = new Scanner(SETTINGS);
    while (sc.hasNext()) {
      String line = sc.nextLine();
      if (line.charAt(0) =='#') {
        continue;
      }
      Scanner s = new Scanner(line).useLocale(Locale.US);
      String option = s.next().toLowerCase();
      if (option.equals("work")) {
        List<Integer> timers = getTimers(s);
        TimersUtil.setWorkTimers(timers);
      } else if (option.equals("play")) {
        List<Integer> timers = getTimers(s);
        TimersUtil.setPlayTimers(timers);
      } else if (option.equals("snooze")) {
        List<Integer> timers = getTimers(s);
        TimersUtil.setSnoozeTimers(timers);
      } else if (option.equals("screen")) {
        double widthPercent = s.nextDouble();
        double heightPercent = s.nextDouble();
        NekoFrame.setWidthPercent(widthPercent);
        NekoFrame.setHeightPercent(heightPercent);
      } else if (option.equals("speed")) {
        int movement = s.nextInt();
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
