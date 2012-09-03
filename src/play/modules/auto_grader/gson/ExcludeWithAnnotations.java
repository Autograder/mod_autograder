package play.modules.auto_grader.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.util.ArrayList;
import java.util.List;

public class ExcludeWithAnnotations implements ExclusionStrategy {
    private final List<Class<?>> typesToSkip;

    public ExcludeWithAnnotations(Class<?> typeToSkip, Class<?>... toSkips) {
        this.typesToSkip = new ArrayList<Class<?>>(toSkips.length + 1);
        this.typesToSkip.add(typeToSkip);
        for (Class<?> clz : toSkips) {
            this.typesToSkip.add(clz);
        }
    }

    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }

    public boolean shouldSkipField(FieldAttributes f) {
        for (Class clazz : typesToSkip) {
            if (f.getAnnotation(clazz) != null)
                return true;
        }

        return false;
    }
}
