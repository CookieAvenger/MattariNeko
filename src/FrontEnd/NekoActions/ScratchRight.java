package FrontEnd.NekoActions;

import FrontEnd.NekoFrame;
import Neko.NekoMovementCoordinator;

public class ScratchRight implements NekoAction {

  private static String SCRATCH_ONE = "rtogi1.png";
  private static String SCRATCH_TWO = "rtogi2.png";

  @Override
  public void doAction(NekoImages images, NekoFrame frame, NekoMovementCoordinator guidance) throws Exception {
    frame.paintNeko(images.get(SCRATCH_ONE));
    Thread.sleep(standardDelay);
    frame.paintNeko(images.get(SCRATCH_TWO));
    Thread.sleep(standardDelay);
  }
}
