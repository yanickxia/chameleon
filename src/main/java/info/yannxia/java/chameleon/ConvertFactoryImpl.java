package info.yannxia.java.chameleon;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by yann on 2016/11/30.
 */
public class ConvertFactoryImpl extends AbstractConvertFactory {

    private ConvertFactoryImpl() {
    }

    public static ConvertFactoryImpl build(Object... objects) {
        ConvertFactoryImpl convertFactory = new ConvertFactoryImpl();
        Arrays.stream(objects)
                .forEach(convertFactory::setupObject);

        return convertFactory;
    }
}


