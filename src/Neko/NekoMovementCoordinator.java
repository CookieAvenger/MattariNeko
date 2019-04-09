package Neko;

import FrontEnd.NekoActions.*;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class NekoMovementCoordinator {

  private static int MAX_MOVEMENT_PER_ACTION = 35;

  private static Queue<NekoAction> sittingCatActions = new LinkedList<>();
  private Point nextActionEndLocation, lastNekoLocation;
  private NekoAction assignedAction;

  public NekoMovementCoordinator(Point nekoLocation, Point goalLocation, boolean sitAtGoal) {
    nextActionEndLocation = approximateNextLocation(nekoLocation, goalLocation);
    if (nextActionEndLocation.equals(goalLocation)) {
      if (sitAtGoal) {
        if (sittingCatActions.isEmpty()) {
          generateSittingCatAction();
        }
        assignedAction = sittingCatActions.poll();
      } else {
        assignedAction = assignScratch(nekoLocation, goalLocation);
      }
    } else {
      if (!sittingCatActions.isEmpty()) {
        sittingCatActions = new LinkedList<>();
      }
      lastNekoLocation = nekoLocation;
      assignedAction = assignMovement(nekoLocation, nextActionEndLocation);
    }
  }

  private void generateSittingCatAction() {
    int numberOfSits = ThreadLocalRandom.current().nextInt(10, 20);
    for (int i = 0; i < numberOfSits; ++i) {
      sittingCatActions.add(new Sit());
    }
    double coinToss = ThreadLocalRandom.current().nextDouble(0, 1);
    if (coinToss < 0.5) {
      sittingCatActions.add(new StandingYawn());
    }
    int numberOfScratches = ThreadLocalRandom.current().nextInt(3, 8);
    for (int i = 0; i < numberOfScratches; ++i) {
      sittingCatActions.add(new ScratchItself());
    }
  }

  private boolean nekoAtGoal(Point nekoLocation, Point goalLocation) {
    Point startOfNekoImage = new Point(nekoLocation.x - NekoImages.IMAGE_WIDTH / 2, nekoLocation.y - NekoImages.IMAGE_HEIGHT / 2);
    Point endOfNekoImage = new Point(nekoLocation.x + NekoImages.IMAGE_WIDTH / 2, nekoLocation.y + NekoImages.IMAGE_HEIGHT / 2);
    return startOfNekoImage.x <= goalLocation.x && goalLocation.x <= endOfNekoImage.x && startOfNekoImage.y <= goalLocation.y && goalLocation.y <= endOfNekoImage.y;
  }

  private Point approximateNextLocation(Point nekoLocation, Point goalLocation) {
    //vector
    int x = goalLocation.x - nekoLocation.x;
    int y = goalLocation.y - nekoLocation.y;
    //vector that adds to one
    double xOne = (double) x / (Math.abs(x) + Math.abs(y));
    double yOne = (double) y / (Math.abs(x) + Math.abs(y));
    //vector that adds to max movement per action
    double xMax = xOne * MAX_MOVEMENT_PER_ACTION;
    double yMax = yOne * MAX_MOVEMENT_PER_ACTION;
    //approx vector
    int xApprox = (int) Math.round(xMax);
    int yApprox = (int) Math.round(yMax);

    if (x < 0) {
      xApprox = Math.max(x, xApprox);
    } else if (x > 0) {
      xApprox = Math.min(x, xApprox);
    } else {
      xApprox = 0;
    }

    if (y < 0) {
      yApprox = Math.max(y, yApprox);
    } else if (y > 0) {
      yApprox = Math.min(y, yApprox);
    } else {
      yApprox = 0;
    }

    return new Point(nekoLocation.x + xApprox, nekoLocation.y + yApprox);
  }

  private NekoAction assignMovement(Point nekoLocation, Point nextActionEndLocation) {
    int x = nextActionEndLocation.x - nekoLocation.x;
    int y = nextActionEndLocation.y - nekoLocation.y;
    double angle = Math.toDegrees(Math.atan2(y,x)) + 180;
    if (angle <= 22.5) {
      return new MoveLeft();
    } else if (angle <= 67.5) {
      return new MoveUpLeft();
    } else if (angle <= 112.5) {
      return new MoveUp();
    } else if (angle <= 157.5) {
      return new MoveUpRight();
    } else if (angle <= 202.5) {
      return new MoveRight();
    } else if (angle <= 247.5) {
      return new MoveDownRight();
    } else if (angle <= 292.5) {
      return new MoveDown();
    } else if (angle <= 337.5) {
      return new MoveDownLeft();
    } else {
      return new MoveLeft();
    }
  }

  private NekoAction assignScratch(Point nekoLocation, Point goalLocation) {
    int x = goalLocation.x - nekoLocation.x;
    int y = goalLocation.y - nekoLocation.y;
    double angle = Math.toDegrees(Math.atan2(y,x)) + 180;
    if (angle <= 45) {
      return new ScratchLeft();
    } else if (angle <= 135) {
      return new ScratchUp();
    } else if (angle <= 225) {
      return new ScratchRight();
    } else if (angle <= 315) {
      return new ScratchDown();
    } else {
      return new ScratchLeft();
    }
  }

  public NekoAction getAssignedAction() {
    return assignedAction;
  }

  public Point getNextMovementLocation(int movementNumber, int numberOfMovements) {
    if (movementNumber == numberOfMovements) {
      return nextActionEndLocation;
    } else {
      int xChange = (int) Math.round((((double) (nextActionEndLocation.x - lastNekoLocation.x) / numberOfMovements)) * movementNumber);
      int yChange = (int) Math.round((((double) (nextActionEndLocation.y - lastNekoLocation.y) / numberOfMovements)) * movementNumber);
      return new Point(lastNekoLocation.x + xChange, lastNekoLocation.y + yChange);
    }
  }

  public static void setMaxMovementPerAction(int maxMovementPerAction) {
    MAX_MOVEMENT_PER_ACTION = maxMovementPerAction;
  }
}
