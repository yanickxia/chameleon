package info.yannxia.java.chameleon;

import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringConvertFactoryImplLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        SpringConvertFactoryImpl springConvertFactory = SpringConvertFactoryImpl.build(applicationContext);

        ConfigurableApplicationContext configContext = (ConfigurableApplicationContext) applicationContext;
        SingletonBeanRegistry beanRegistry = configContext.getBeanFactory();
        beanRegistry.registerSingleton("convertFactory", springConvertFactory);
    }

}
