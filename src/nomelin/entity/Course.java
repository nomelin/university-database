package nomelin.entity;

public record Course(String ID, String name, float credit) {
    @Override
    public String toString() {
        return "Course{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", credit=" + credit +
                '}';
    }
}
