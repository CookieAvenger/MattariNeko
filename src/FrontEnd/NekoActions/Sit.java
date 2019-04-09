package FrontEnd.NekoActions;

import FrontEnd.NekoFrame;
import FrontEnd.NekoImages;
import Neko.NekoMovementCoordinator;

public class Sit implements NekoAction {

  @Override
  public void doAction(NekoImages images, NekoFrame frame, NekoMovementCoordinator guidance)
      throws Exception {
    frame.paintNeko(images.get("mati1.png"));
    Thread.sleep(standardDelay);
  }
}
