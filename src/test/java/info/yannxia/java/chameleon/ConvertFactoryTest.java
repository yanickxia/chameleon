package info.yannxia.java.chameleon;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yann on 2016/11/30.
 */
public class ConvertFactoryTest {
    ConvertFactory convertFactory;

    @Test
    public void convert() throws Exception {
        convertFactory = ConvertFactoryImpl.build(new ConvertTestService());

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

