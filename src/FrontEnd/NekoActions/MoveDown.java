package FrontEnd.NekoActions;

import FrontEnd.NekoFrame;
import FrontEnd.NekoImages;
import Neko.NekoMovementCoordinator;

public class MoveDown implements NekoAction {

  private static String DOWN_ONE = "down1.png";
  private static String DOWN_TWO = "down2.png";

  @Override
  public void doAction(NekoImages images, NekoFrame frame, NekoMovementCoordinator guidance) throws Exception {
    frame.paintNeko(images.get(DOWN_ONE));
    frame.setNekoLocation(guidance.getNextMovementLocation(1, 2));
    Thread.sleep(standardDelay);
    frame.paintNeko(images.get(DOWN_TWO));
    frame.setNekoLocation(guidance.getNextMovementLocation(2, 2));
    Thread.sleep(standardDelay);
  }
}
