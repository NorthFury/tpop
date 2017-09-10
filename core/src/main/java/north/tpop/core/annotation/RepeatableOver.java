package north.tpop.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import north.tpop.core.Element;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RepeatableOver {
    public Class<? extends Element> value() default Element.class;
}
