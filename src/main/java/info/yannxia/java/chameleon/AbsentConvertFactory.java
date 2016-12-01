package info.yannxia.java.chameleon;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by yann on 2016/12/1.
 */
public abstract class AbsentConvertFactory implements ConvertFactory {

    protected ConcurrentHashMap<ConvertKey, CovertInstant> keyCovertInstantConcurrentHashMap = new ConcurrentHashMap<>();

    public <T> T convert(Class<T> expect, Object... params) {

        //get all paramsClass
        Class[] paramClasses = Arrays.stream(params)
                .map(Object::getClass)
                .toArray(Class[]::new);

        ConvertKey convertKey = new ConvertKey(expect, paramClasses);
        CovertInstant covertInstant = keyCovertInstantConcurrentHashMap.get(convertKey);

        if (covertInstant == null) {
            throw new RuntimeException("not found match convertor");
        }

        try {
            return (T) covertInstant.covertMethod.invoke(covertInstant.convertObj, params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
