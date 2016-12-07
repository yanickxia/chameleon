package info.yannxia.java.chameleon;

import info.yannxia.java.chameleon.annonation.Convertor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yann on 2016/12/1.
 */
public abstract class AbstractConvertFactory implements ConvertFactory {

    protected ConcurrentHashMap<ConvertKey, CovertInstant> keyCovertInstantConcurrentHashMap = new ConcurrentHashMap<>();

    public <T> T convert(Class<T> expect, Object... params) {

        //get all paramsClass
        Class[] paramClasses = Arrays.stream(params)
                .map(Object::getClass)
                .toArray(Class[]::new);

        //get cached Converter
        ConvertKey convertKey = new ConvertKey(expect, paramClasses);
        CovertInstant covertInstant = keyCovertInstantConcurrentHashMap.get(convertKey);

        if (covertInstant == null) {
            throw new RuntimeException("not found match converter");
        }

        try {
            return (T) covertInstant.covertMethod.invoke(covertInstant.convertObj, params);
        } catch (Exception e) {
            throw new RuntimeException("invoke convert function error", e);
        }
    }

    void setupObject(Object object) {
        Method[] methods = object.getClass().getMethods();
        Arrays.stream(methods)
                .filter(AbstractConvertFactory::isConvertorMethod)
                .forEach(method -> {
                    Class[] froms = method.getParameterTypes();
                    Class to = method.getReturnType();
                    ConvertKey convertKey = new ConvertKey(to, froms);
                    CovertInstant covertInstant = new CovertInstant(object, method);

                    if (this.keyCovertInstantConcurrentHashMap.containsKey(convertKey)) {
                        throw new IllegalArgumentException(String.format("already has [%s] -> [%s] method", froms, to));
                    }

                    this.keyCovertInstantConcurrentHashMap.put(convertKey, covertInstant);
                });
    }

    private static boolean isConvertorMethod(Method method) {
        return method.getAnnotation(Convertor.class) != null;
    }
}
