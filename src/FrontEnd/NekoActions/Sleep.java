package FrontEnd.NekoActions;

import FrontEnd.NekoFrame;
import Neko.NekoMovementCoordinator;

public class Sleep implements NekoAction {

  private static String SLEEP_ONE = "sleep1.png";
  private static String SLEEP_TWO = "sleep2.png";

  @Override
  public void doAction(NekoImages images, NekoFrame frame, NekoMovementCoordinator guidance) throws Exception {
    frame.paintNeko(images.get(SLEEP_ONE));
    Thread.sleep(standardDelay);
    frame.paintNeko(images.get(SLEEP_TWO));
    Thread.sleep(standardDelay);
  }
}
