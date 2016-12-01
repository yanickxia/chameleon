package info.yannxia.java.chameleon;

import java.lang.reflect.Method;

class CovertInstant {
    Object convertObj;
    Method covertMethod;

    public CovertInstant(Object convertObj, Method covertMethod) {
        this.convertObj = convertObj;
        this.covertMethod = covertMethod;
    }
}
