package Neko;

import FrontEnd.NekoActions.NekoImages;
import FrontEnd.NekoActions.SittingYawn;
import FrontEnd.NekoActions.Sleep;
import FrontEnd.NekoActions.WakingUp;
import FrontEnd.NekoFrame;
import java.awt.Dimension;
import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

public class MattariNeko {

  private static NekoFrame frame;
  private static boolean nekoReleased = true;
  private static boolean nekoFinished = false;
  private static NekoImages images;

  public static void main(String[] args) {
    new Settings();
    images = new NekoImages();
    frame = new NekoFrame();
    frame.setVisible(true);
    //nekoFinished needs to be other places too
    while (!nekoFinished) {
      nekoRun();
      nekoStop();
    }
    nekoExit();
  }

  public static void flipNeko() {
    nekoReleased = !nekoReleased;
  }

  //set location at start from file
  //have ability to return to it from wherever it is

  public static void nekoRun() {
    NekoImages images = new NekoImages();
    try {
      new WakingUp().doAction(images, frame, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    while (nekoReleased) {
      try {
        NekoMovementCoordinator nextMove = new NekoMovementCoordinator(frame.getNekoLocation(), frame.getMouseLocation(), false);
        nextMove.getAssignedAction().doAction(images, frame, nextMove);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void nekoStop() {
    nekoToLocation(frame.getStartingLocation());
    try {
      new SittingYawn().doAction(images,frame, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    while (!nekoReleased) {
      try {
        new Sleep().doAction(images, frame, null);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private static void nekoToLocation(Point goalLocation) {
    while (!frame.getNekoLocation().equals(frame.getValidNekoLocation(goalLocation))) {
      try {
        System.out.println("f " + frame.getNekoLocation());
        System.out.println("g " + frame.getValidNekoLocation(goalLocation));
        NekoMovementCoordinator nextMove = new NekoMovementCoordinator(frame.getNekoLocation(), frame.getValidNekoLocation(goalLocation), true);
        nextMove.getAssignedAction().doAction(images, frame, nextMove);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void nekoExit() {
    nekoReleased = false;
    nekoReleased = true;
    nekoReleased = false;

    Dimension screen = frame.getScreenSize();
    int perimeter = screen.height*2 + screen.width*2;
    int pointOnPerimeter = ThreadLocalRandom.current().nextInt(0, perimeter);
    Point pointOnScreen = perimeterToScreen(pointOnPerimeter, screen);
    nekoToLocation(frame.getValidNekoLocation(pointOnScreen));
  }

  private static Point perimeterToScreen(int pointOnPerimeter, Dimension screen) {
    if (pointOnPerimeter < screen.width) {
      return new Point(0, pointOnPerimeter);
    }
    pointOnPerimeter -= screen.width;
    if (pointOnPerimeter < screen.height) {
      return new Point(screen.width, pointOnPerimeter);
    }
    pointOnPerimeter -= screen.height;
    if (pointOnPerimeter < screen.width) {
      return new Point(pointOnPerimeter, screen.height);
    }
    pointOnPerimeter -= screen.width;
    return new Point(0, pointOnPerimeter);
  }
}

