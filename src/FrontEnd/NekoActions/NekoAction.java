package FrontEnd.NekoActions;

import FrontEnd.NekoFrame;
import FrontEnd.NekoImages;
import Neko.NekoMovementCoordinator;

public interface NekoAction {
  static int standardMovement = 10, standardDelay = 150;
  public void doAction(NekoImages images, NekoFrame frame, NekoMovementCoordinator guidance) throws Exception;
}
