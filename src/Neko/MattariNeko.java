package Neko;

import FrontEnd.NekoActions.NekoAction;
import FrontEnd.NekoImages;
import FrontEnd.NekoActions.SittingYawn;
import FrontEnd.NekoActions.Sleep;
import FrontEnd.NekoActions.WakingUp;
import FrontEnd.NekoFrame;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

public class MattariNeko {

  private static NekoFrame frame;
  private static boolean nekoAwake = true;
  private static boolean nekoUp = false;
  private static boolean nekoFinished = false;
  private static NekoImages images;

  public static void main(String[] args) {
    new Settings();
    images = new NekoImages();
    frame = new NekoFrame();
    frame.setVisible(true);
    while (!nekoFinished) {
      nekoRun();
      if (!nekoFinished) {
        nekoStop();
      }
    }
    nekoExit();
    System.exit(0);
  }

  public static void flipNekoAwake() {
    nekoAwake = !nekoAwake;
  }

  public static void exitNeko() {
    nekoFinished = true;
    flipNekoAwake();
  }

  public static void setNekoUp() {
    nekoUp = true;
  }

  public static void setNekoDown() {
    nekoUp = false;
  }

  public static void nekoRun() {
    NekoImages images = new NekoImages();
    try {
      new WakingUp().doAction(images, frame, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    while (nekoAwake) {
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
    nekoAboutToSleep();
    while (!nekoAwake) {
      try {
        if (nekoUp) {
          new WakingUp().doAction(images, frame, null);
          Queue<NekoAction> sittingCatActions = new LinkedList<>();
          while (nekoUp) {
            if (sittingCatActions.isEmpty()) {
              sittingCatActions.addAll(NekoMovementCoordinator.generateSittingCatAction());
            }
            sittingCatActions.poll().doAction(images, frame, null);
          }
          nekoAboutToSleep();
        } else {
          new Sleep().doAction(images, frame, null);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private static void nekoAboutToSleep() {
    try {
      for (int i = 0; i < 2; ++i) {
        new SittingYawn().doAction(images, frame, null);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void nekoToLocation(Point goalLocation) {
    Point validGoalLocation = frame.getValidNekoLocation(goalLocation);
    while (!validGoalLocation.equals(frame.getNekoLocation())) {
      try {
        NekoMovementCoordinator nextMove = new NekoMovementCoordinator(frame.getNekoLocation(), validGoalLocation, true);
        nextMove.getAssignedAction().doAction(images, frame, nextMove);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void nekoExit() {
    try {
      new Sleep().doAction(images, frame, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
//    Dimension screen = NekoFrame.getScreenSize();
//    int perimeter = screen.height*2 + screen.width*2;
//    int pointOnPerimeter = ThreadLocalRandom.current().nextInt(0, perimeter);
//    Point pointOnScreen = perimeterToScreen(pointOnPerimeter, screen);
//    nekoToLocation(frame.getValidNekoLocation(pointOnScreen));
  }

//  private static Point perimeterToScreen(int pointOnPerimeter, Dimension screen) {
//    if (pointOnPerimeter < screen.width) {
//      return new Point(0, pointOnPerimeter);
//    }
//    pointOnPerimeter -= screen.width;
//    if (pointOnPerimeter < screen.height) {
//      return new Point(screen.width, pointOnPerimeter);
//    }
//    pointOnPerimeter -= screen.height;
//    if (pointOnPerimeter < screen.width) {
//      return new Point(pointOnPerimeter, screen.height);
//    }
//    pointOnPerimeter -= screen.width;
//    return new Point(0, pointOnPerimeter);
//  }
}

