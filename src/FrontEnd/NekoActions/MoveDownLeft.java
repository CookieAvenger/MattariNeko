package FrontEnd.NekoActions;

import FrontEnd.NekoFrame;
import FrontEnd.NekoImages;
import Neko.NekoMovementCoordinator;

public class MoveDownLeft implements NekoAction{

  private static String DOWN_LEFT_ONE = "dwleft1.png";
  private static String DOWN_LEFT_TWO = "dwleft2.png";

  @Override
  public void doAction(NekoImages images, NekoFrame frame, NekoMovementCoordinator guidance) throws Exception {
    frame.paintNeko(images.get(DOWN_LEFT_ONE));
    frame.setNekoLocation(guidance.getNextMovementLocation(1, 2));
    Thread.sleep(standardDelay);
    frame.paintNeko(images.get(DOWN_LEFT_TWO));
    frame.setNekoLocation(guidance.getNextMovementLocation(2, 2));
    Thread.sleep(standardDelay);
  }
}
