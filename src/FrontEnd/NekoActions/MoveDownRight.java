package FrontEnd.NekoActions;

import FrontEnd.NekoFrame;
import FrontEnd.NekoImages;
import Neko.NekoMovementCoordinator;

public class MoveDownRight implements NekoAction{

  private static String DOWN_RIGHT_ONE = "dwright1.png";
  private static String DOWN_RIGHT_TWO = "dwright2.png";

  @Override
  public void doAction(NekoImages images, NekoFrame frame, NekoMovementCoordinator guidance) throws Exception {
    frame.paintNeko(images.get(DOWN_RIGHT_ONE));
    frame.setNekoLocation(guidance.getNextMovementLocation(1, 2));
    Thread.sleep(standardDelay);
    frame.paintNeko(images.get(DOWN_RIGHT_TWO));
    frame.setNekoLocation(guidance.getNextMovementLocation(2, 2));
    Thread.sleep(standardDelay);
  }
}
