package FrontEnd.NekoActions;

import FrontEnd.NekoFrame;
import Neko.NekoMovementCoordinator;

public class StandingYawn implements NekoAction {

  private static String STILL = "mati1.png";
  private static String YAWN = "mati2.png";

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
