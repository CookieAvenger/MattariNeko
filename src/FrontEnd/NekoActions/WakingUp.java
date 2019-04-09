package FrontEnd.NekoActions;

import FrontEnd.NekoFrame;
import FrontEnd.NekoImages;
import Neko.NekoMovementCoordinator;

public class WakingUp implements NekoAction {

  private static String STILL = "mati1.png";
  private static String AWAKE = "awake.png";

  @Override
  public void doAction(NekoImages images, NekoFrame frame, NekoMovementCoordinator guidance) throws Exception {
    frame.paintNeko(images.get(AWAKE));
    Thread.sleep(standardDelay);
    frame.paintNeko(images.get(STILL));
    Thread.sleep(standardDelay);
  }
}
