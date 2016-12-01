package info.yannxia.java.chameleon;

import info.yannxia.java.chameleon.annonation.Convertor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yann on 2016/11/30.
 */
public class ConvertFactoryImpl extends AbsentConvertFactory {

    private ConvertFactoryImpl() {
    }

    public static ConvertFactoryImpl build(Object... objects) {
        ConvertFactoryImpl convertFactory = new ConvertFactoryImpl();
        Arrays.stream(objects)
                .forEach(object -> {
                    Method[] methods = object.getClass().getMethods();
                    Arrays.stream(methods)
                            .filter(method -> method.getAnnotation(Convertor.class) != null)
                            .forEach(method -> {
                                Class[] froms = method.getParameterTypes();
                                Class to = method.getReturnType();
                                ConvertKey convertKey = new ConvertKey(to, froms);
                                CovertInstant covertInstant = null;
                                try {
                                    covertInstant = new CovertInstant(object, object.getClass(), method);
                                    convertFactory.keyCovertInstantConcurrentHashMap.put(convertKey, covertInstant);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                });

        return convertFactory;
    }
}


