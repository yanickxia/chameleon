package info.yannxia.java.chameleon;

import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by yann on 2016/11/30.
 */
public class SpringConvertFactoryImpl extends AbstractConvertFactory {

    private ApplicationContext applicationContext;

    private SpringConvertFactoryImpl() {
    }

    public static SpringConvertFactoryImpl build(ApplicationContext applicationContext) {
        SpringConvertFactoryImpl springConvertFactory = new SpringConvertFactoryImpl();
        springConvertFactory.applicationContext = applicationContext;

        String[] beanNames = springConvertFactory.applicationContext.getBeanDefinitionNames();
        Arrays.stream(beanNames)
                .forEach(beanName -> {
                    Object o = springConvertFactory.applicationContext.getBean(beanName);
                    springConvertFactory.setupObject(o);
                });

        return springConvertFactory;
    }

}


