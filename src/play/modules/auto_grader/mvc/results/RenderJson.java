package play.modules.auto_grader.mvc.results;

import com.auto_grader.annotations.Exclude;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import play.mvc.Http.Request;
import play.mvc.Http.Response;
import play.exceptions.UnexpectedException;

/**
 * 200 OK with application/json
 */
public class RenderJson extends Result {

    private static class MyExclusionStrategy implements ExclusionStrategy {
        private final Class<?> typeToSkip;

        public MyExclusionStrategy() {
            this(null);
        }

        private MyExclusionStrategy(Class<?> typeToSkip) {
            this.typeToSkip = typeToSkip;
        }

        public boolean shouldSkipClass(Class<?> clazz) {
            return (clazz == typeToSkip);
        }

        public boolean shouldSkipField(FieldAttributes f) {
            return f.getAnnotation(Exclude.class) != null;
        }
    }

    String json;
    Gson gson;

    private RenderJson() {
        this.gson = new GsonBuilder()
            .setExclusionStrategies(new models.MyExclusionStrategy())
            .create();
    }

    public RenderJson(Object o) {
        this();
        json = gson.toJson(o);
    }

    public RenderJson(Object o, Type type) {
        this();
        json = gson.toJson(o, type);
    }

    public RenderJson(String jsonString) {
        json = jsonString;
    }

    public void apply(Request request, Response response) {
        try {
            String encoding = getEncoding();
            setContentTypeIfNotSet(response, "application/json; charset="+encoding);
            response.out.write(json.getBytes(encoding));
        } catch (Exception e) {
            throw new UnexpectedException(e);
        }
    }

    //
    static Method getMethod(Class<?> clazz, String name) {
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }
}
