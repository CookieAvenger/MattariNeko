package Neko;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class ActivityTracker {

  final static File ACTIVITY_LOG = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "MattariNekoActivity.log");
  private static Activity currentActivity = null;

  public static void ActivityBegun(String type, Duration intendedTime) {
    if (currentActivity != null) {
      ActivityFinish(null);
    }
    currentActivity = new Activity(type, intendedTime);
  }

  public static void ActivityFinish(String description) {
    if (currentActivity == null) {
      return;
    }
    currentActivity.finishActivity(description);
    createFileIfNotExists();
    appendToActivityLog(currentActivity.toString());
    currentActivity = null;
  }

  public static void createFileIfNotExists() {
    if (!ACTIVITY_LOG.exists()) {
      try {
        ACTIVITY_LOG.createNewFile();
        appendToActivityLog("Activity Type | Intended Time |  Actual Duration | Start Time | End Time  | Description" + System.lineSeparator());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static void appendToActivityLog(String line) {
    try {
      FileWriter fw = new FileWriter(ACTIVITY_LOG, true);
      fw.append(line);
      fw.append(System.lineSeparator());
      fw.flush();
      fw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static class Activity {
    private String type, description = "N/A";
    private Instant startTime, endTime;
    private Duration intendedTime, timePeriod;

    public Activity(String type, Duration intendedTime) {
      this.startTime = Instant.now();
      this.type = type;
      this.intendedTime = intendedTime;
    }

    public void finishActivity(String description) {
      endTime = Instant.now();
      if (description != null && !description.trim().isEmpty()) {
        this.description = description;
      }
      timePeriod = Duration.between(startTime, endTime);
    }

    public String toString() {
      return type + " | " + intendedTime + " | " + timePeriod + " | "+ startTime + " | " + endTime + " | " + description;
    }
  }

}
