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
        convertFactory = ConvertFactoryImpl.build(ConvertTestService.class);

        A a = new A();
        a.name = "1111";

        B b = convertFactory.convert(a, B.class);
        Assert.assertEquals("1111", b.name);
    }

}

