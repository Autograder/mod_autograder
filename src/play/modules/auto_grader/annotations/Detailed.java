package play.modules.auto_grader.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Detailed {
    // Field tag only annotation
}
