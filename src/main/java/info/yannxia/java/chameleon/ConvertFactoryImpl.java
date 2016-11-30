package info.yannxia.java.chameleon;

import info.yannxia.java.chameleon.annonation.Convertor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yann on 2016/11/30.
 */
public class ConvertFactoryImpl implements ConvertFactory {

    private ConcurrentHashMap<ConvertKey, CovertInstant> keyCovertInstantConcurrentHashMap = new ConcurrentHashMap<>();

    private ConvertFactoryImpl() {
    }

    public <T> T convert(Object a, Class<T> b) {
        ConvertKey convertKey = new ConvertKey(a.getClass(), b);
        CovertInstant covertInstant = keyCovertInstantConcurrentHashMap.get(convertKey);

        if (covertInstant == null) {
            throw new RuntimeException("not found");
        }

        try {
            return (T) covertInstant.covertMethod.invoke(covertInstant.convert.newInstance(), a);
        } catch (Exception e) {

        }

        return null;
    }

    public static ConvertFactoryImpl build(Class... classes) {
        ConvertFactoryImpl convertFactory = new ConvertFactoryImpl();
        Arrays.stream(classes)
                .forEach(clazz -> {
                    Method[] methods = clazz.getMethods();
                    Arrays.stream(methods)
                            .filter(method -> method.getAnnotation(Convertor.class) != null)
                            .forEach(method -> {
                                Class from = method.getParameterTypes()[0];
                                Class to = method.getReturnType();
                                ConvertKey convertKey = new ConvertKey(from, to);
                                CovertInstant covertInstant = null;
                                try {
                                    covertInstant = new CovertInstant(clazz.newInstance(), clazz, method);
                                    convertFactory.keyCovertInstantConcurrentHashMap.put(convertKey, covertInstant);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                });

        return convertFactory;
    }
}


