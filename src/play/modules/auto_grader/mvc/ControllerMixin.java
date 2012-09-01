package play.modules.auto_grader.mvc;

import play.modules.auto_grader.MyExclusionStrategy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

import play.mvc.Controller;

public class ControllerMixin extends Controller {

    private static Gson gson = new GsonBuilder()
            .setExclusionStrategies(new MyExclusionStrategy())
            .create();

    protected static void renderJson(Object o) {
        renderJSON(gson.toJson(o));
    }
}