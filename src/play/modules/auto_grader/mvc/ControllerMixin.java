package play.modules.auto_grader.mvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import play.modules.auto_grader.annotations.*;
import play.modules.auto_grader.gson.ExcludeWithAnnotations;

import play.mvc.Controller;

public class ControllerMixin extends Controller {

    protected static void renderJson(Object o, Class<?>... annotations) {
        Gson gson = new GsonBuilder()
            .setExclusionStrategies(new ExcludeWithAnnotations(Exclude.class, annotations))
            .create();

        renderJSON(gson.toJson(o));
    }
}