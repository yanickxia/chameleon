package info.yannxia.java.chameleon;

import info.yannxia.java.chameleon.annonation.Convertor;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedList;
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
                                CovertInstant covertInstant = new CovertInstant(clazz, method);

                                convertFactory.keyCovertInstantConcurrentHashMap.put(convertKey, covertInstant);
                            });
                });

        return convertFactory;
    }
}


class ConvertKey {
    Class from;
    Class to;

    public ConvertKey(Class from, Class to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public int hashCode() {
        return from.getName().hashCode() & to.getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ConvertKey) {
            return ((ConvertKey) obj).from.getName().equals(((ConvertKey) obj).from.getName());
        }

        return super.equals(obj);
    }
}

class CovertInstant {
    Object convertObj;
    Class convert;
    Method covertMethod;

    public CovertInstant(Class convert, Method covertMethod) {
        this.convert = convert;
        this.covertMethod = covertMethod;
    }

    public CovertInstant(Object convertObj, Class convert, Method covertMethod) {
        this.convertObj = convertObj;
        this.convert = convert;
        this.covertMethod = covertMethod;
    }
}

