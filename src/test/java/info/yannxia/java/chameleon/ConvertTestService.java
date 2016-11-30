package info.yannxia.java.chameleon;

import info.yannxia.java.chameleon.annonation.Convertor;
import org.springframework.stereotype.Component;

@Component
class ConvertTestService {

    @Convertor
    public B toB(A a) {
        B b = new B();
        b.name = a.name;
        return b;
    }
}
