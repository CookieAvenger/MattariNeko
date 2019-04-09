package FrontEnd.NekoActions;

import FrontEnd.NekoFrame;
import Neko.NekoMovementCoordinator;

public class MoveLeft implements NekoAction {

  private static String LEFT_ONE = "left1.png";
  private static String LEFT_TWO = "left2.png";

  @Override
  public void doAction(NekoImages images, NekoFrame frame, NekoMovementCoordinator guidance) throws Exception {
    frame.paintNeko(images.get(LEFT_ONE));
    frame.setNekoLocation(guidance.getNextMovementLocation(1, 2));
    Thread.sleep(standardDelay);
    frame.paintNeko(images.get(LEFT_TWO));
    frame.setNekoLocation(guidance.getNextMovementLocation(2,2));
    Thread.sleep(standardDelay);
  }
}
