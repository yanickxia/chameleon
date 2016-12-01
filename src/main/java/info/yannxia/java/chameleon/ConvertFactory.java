package info.yannxia.java.chameleon;

/**
 * Created by yann on 2016/11/30.
 */
public interface ConvertFactory {

    <T> T convert(Class<T> expect, Object... params);
}
