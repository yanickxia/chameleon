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

    @Convertor
    public C toC(A a, B b){
        C c = new C();
        c.aName = a.name;
        c.bName = b.name;
        return c;
    }

    static class A {
        String name;
    }

    static class B {
        String name;
    }

    static class C {
        String aName;
        String bName;
    }
}
