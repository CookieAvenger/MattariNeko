package FrontEnd.NekoActions;

import FrontEnd.NekoFrame;
import Neko.NekoMovementCoordinator;

public class MoveUp implements NekoAction {

  private static String UP_ONE = "up1.png";
  private static String UP_TWO = "up2.png";

  @Override
  public void doAction(NekoImages images, NekoFrame frame, NekoMovementCoordinator guidance) throws Exception {
    frame.paintNeko(images.get(UP_ONE));
    frame.setNekoLocation(guidance.getNextMovementLocation(1,2));
    Thread.sleep(standardDelay);
    frame.paintNeko(images.get(UP_TWO));
    frame.setNekoLocation(guidance.getNextMovementLocation(2,2));
    Thread.sleep(standardDelay);
  }
}
