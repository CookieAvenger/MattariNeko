package FrontEnd.NekoActions;

import FrontEnd.NekoFrame;
import FrontEnd.NekoImages;
import Neko.NekoMovementCoordinator;

public class MoveUpLeft implements NekoAction{

  private static String UP_LEFT_ONE = "upleft1.png";
  private static String UP_LEFT_TWO = "upleft2.png";

  @Override
  public void doAction(NekoImages images, NekoFrame frame, NekoMovementCoordinator guidance) throws Exception {
    frame.paintNeko(images.get(UP_LEFT_ONE));
    frame.setNekoLocation(guidance.getNextMovementLocation(1,2));
    Thread.sleep(standardDelay);
    frame.paintNeko(images.get(UP_LEFT_TWO));
    frame.setNekoLocation(guidance.getNextMovementLocation(2,2));
    Thread.sleep(standardDelay);
  }
}
