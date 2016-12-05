package info.yannxia.java.chameleon;

/**
 * Created by yann on 2016/11/30.
 */
public interface ConvertFactory {

    String SPRING_BEAN_NAME = "convertFactory";

    <T> T convert(Class<T> expect, Object... params);
}
