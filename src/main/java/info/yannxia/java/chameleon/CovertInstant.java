package info.yannxia.java.chameleon;

import java.lang.reflect.Method;

class CovertInstant {
    Object convertObj;
    Class convert;
    Method covertMethod;

    public CovertInstant(Object convertObj, Class convert, Method covertMethod) {
        this.convertObj = convertObj;
        this.convert = convert;
        this.covertMethod = covertMethod;
    }
}
