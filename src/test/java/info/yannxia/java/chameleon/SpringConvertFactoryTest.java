package info.yannxia.java.chameleon;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringConvertFactoryTest {

    @Test
    public void test_spring_convert_factory() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext("info.yannxia.java.chameleon");

        ConvertFactory convertFactory = applicationContext.getBean(ConvertFactory.class);

        A a = new A();
        a.name = "test";

        Assert.assertTrue(convertFactory.convert(a, B.class).name.equals("test"));
    }
}
