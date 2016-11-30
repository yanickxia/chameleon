package info.yannxia.java.chameleon.annonation;

import java.lang.annotation.*;

/**
 * Created by yann on 2016/11/30.
 */

@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Convertor {
}
