package play.modules.auto_grader.mvc;

import com.auto_grader.mvc.results.RenderJson;

public class ControllerMixin {

    protected static void renderJson(Object o) {
        throw new RenderJson(o);
    }
}