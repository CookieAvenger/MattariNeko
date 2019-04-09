package FrontEnd.NekoActions;

import FrontEnd.NekoFrame;
import FrontEnd.NekoImages;
import Neko.NekoMovementCoordinator;

public class MoveUpRight implements NekoAction {

  private static String UP_RIGHT_ONE = "upright1.png";
  private static String UP_RIGHT_TWO = "upright2.png";

  @Override
  public void doAction(NekoImages images, NekoFrame frame, NekoMovementCoordinator guidance) throws Exception {
    frame.paintNeko(images.get(UP_RIGHT_ONE));
    frame.setNekoLocation(guidance.getNextMovementLocation(1,2));
    Thread.sleep(standardDelay);
    frame.paintNeko(images.get(UP_RIGHT_TWO));
    frame.setNekoLocation(guidance.getNextMovementLocation(2,2));
    Thread.sleep(standardDelay);
  }
}
