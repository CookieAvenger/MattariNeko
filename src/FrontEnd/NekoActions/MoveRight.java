package FrontEnd.NekoActions;

import FrontEnd.NekoFrame;
import FrontEnd.NekoImages;
import Neko.NekoMovementCoordinator;

public class MoveRight implements NekoAction{

  private static String RIGHT_ONE = "right1.png";
  private static String RIGHT_TWO = "right2.png";

  @Override
  public void doAction(NekoImages images, NekoFrame frame, NekoMovementCoordinator guidance) throws Exception {
    frame.paintNeko(images.get(RIGHT_ONE));
    frame.setNekoLocation(guidance.getNextMovementLocation(1, 2));
    Thread.sleep(standardDelay);
    frame.paintNeko(images.get(RIGHT_TWO));
    frame.setNekoLocation(guidance.getNextMovementLocation(2, 2));
    Thread.sleep(standardDelay);
  }
}
