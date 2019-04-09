package FrontEnd.NekoActions;

import FrontEnd.NekoFrame;
import FrontEnd.NekoImages;
import Neko.NekoMovementCoordinator;

public class SittingYawn implements NekoAction {

  private static String STILL = "akubi1.png";
  private static String YAWN = "akubi2.png";

  @Override
  public void doAction(NekoImages images, NekoFrame frame, NekoMovementCoordinator guidance) throws Exception {
    frame.paintNeko(images.get(STILL));
    Thread.sleep(standardDelay);
    frame.paintNeko(images.get(YAWN));
    Thread.sleep(standardDelay);
    frame.paintNeko(images.get(STILL));
    Thread.sleep(standardDelay);
  }
}
