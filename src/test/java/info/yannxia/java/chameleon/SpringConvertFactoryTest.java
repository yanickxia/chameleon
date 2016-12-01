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

        ConvertTestService.A a = new ConvertTestService.A();
        a.name = "a";

        ConvertTestService.B b = convertFactory.convert(ConvertTestService.B.class, a);
        Assert.assertEquals(a.name, b.name);
        b.name = "b";

        ConvertTestService.C c = convertFactory.convert(ConvertTestService.C.class, a, b);
        Assert.assertEquals(c.aName, a.name);
        Assert.assertEquals(c.bName, b.name);
    }
}
