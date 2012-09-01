package play.modules.auto_grader;

import play.Logger;
import play.Play;
import play.PlayPlugin;

public class MyPlugin extends PlayPlugin {

   public void onApplicationStart() {
      Logger.info("Yeeha, firstmodule started");
   }

}
