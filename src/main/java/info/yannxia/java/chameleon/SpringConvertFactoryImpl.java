package info.yannxia.java.chameleon;

import info.yannxia.java.chameleon.annonation.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yann on 2016/11/30.
 */
public class SpringConvertFactoryImpl implements ConvertFactory {

    private ConcurrentHashMap<ConvertKey, CovertInstant> keyCovertInstantConcurrentHashMap = new ConcurrentHashMap<>();

    public ApplicationContext applicationContext;

    public SpringConvertFactoryImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        setup();
    }

    private SpringConvertFactoryImpl() {
    }

    public <T> T convert(Object a, Class<T> b) {
        ConvertKey convertKey = new ConvertKey(a.getClass(), b);
        CovertInstant covertInstant = keyCovertInstantConcurrentHashMap.get(convertKey);

        if (covertInstant == null) {
            throw new RuntimeException("not found");
        }

        try {
            return (T) covertInstant.covertMethod.invoke(covertInstant.convertObj, a);
        } catch (Exception e) {

        }

        return null;
    }

    private void setup() {
        String[] beanNames = this.applicationContext.getBeanDefinitionNames();
        Arrays.stream(beanNames)
                .forEach(beanName -> {
                    Object o = this.applicationContext.getBean(beanName);
                    build(o.getClass());
                });
    }


    private void build(Object obj) {

        Method[] methods = obj.getClass().getMethods();
        Arrays.stream(methods)
                .filter(method -> method.getAnnotation(Convertor.class) != null)
                .forEach(method -> {
                    Class from = method.getParameterTypes()[0];
                    Class to = method.getReturnType();
                    ConvertKey convertKey = new ConvertKey(from, to);
                    CovertInstant covertInstant = new CovertInstant(obj, obj.getClass(), method);
                    this.keyCovertInstantConcurrentHashMap.put(convertKey, covertInstant);
                });

    }
}


