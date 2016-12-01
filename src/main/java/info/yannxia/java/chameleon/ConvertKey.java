package info.yannxia.java.chameleon;

import java.util.Arrays;
import java.util.List;

class ConvertKey {
    private List<Class> froms;
    private Class to;

    public ConvertKey(Class to, Class... froms) {
        this.froms = Arrays.asList(froms);
        this.to = to;
    }

    @Override
    public int hashCode() {
        int hashCode = to.getName().hashCode();
        for (Class from : froms) {
            hashCode &= from.getName().hashCode();
        }


        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ConvertKey) {
            ConvertKey other = (ConvertKey) obj;
            return other.froms.equals(this.froms) && other.to.equals(this.to);
        }

        return super.equals(obj);
    }
}
