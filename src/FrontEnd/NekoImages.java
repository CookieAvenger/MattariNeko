package FrontEnd;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class NekoImages {

  Map<String, Image> nekoImages;
  public static int IMAGE_HEIGHT = 32;
  public static int IMAGE_WIDTH = 32;

  public NekoImages() {
    File directory = new File("NekoImages");
    File[] contents = directory.listFiles();
    if (contents == null || contents.length == 0) {
      try {
        throw new Exception("No neko images in found");
      } catch (Exception e) {
        e.printStackTrace();
        System.exit(1);
      }
    }
    nekoImages = new HashMap<>();
    Toolkit tk = Toolkit.getDefaultToolkit();
    for (File f : contents) {
      nekoImages.put(f.getName(), tk.getImage(f.getAbsolutePath()));
    }
  }

  public Image get(String image) throws Exception {
    if (nekoImages.containsKey(image)) {
      return nekoImages.get(image);
    } else {
      throw new Exception(image + " image not found");
    }
  }

}
