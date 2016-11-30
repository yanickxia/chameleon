package info.yannxia.java.chameleon;

class ConvertKey {
    Class from;
    Class to;

    public ConvertKey(Class from, Class to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public int hashCode() {
        return from.getName().hashCode() & to.getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ConvertKey) {
            return ((ConvertKey) obj).from.getName().equals(((ConvertKey) obj).from.getName());
        }

        return super.equals(obj);
    }
}
