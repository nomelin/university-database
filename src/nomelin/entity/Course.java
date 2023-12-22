package nomelin.entity;

public record Course(String ID, String name, float credit) {
    @Override
    public String toString() {
        return "课程信息{" +
                "课程代号='" + ID + '\'' +
                ", 课程名='" + name + '\'' +
                ", 学分=" + credit +
                '}';
    }
}
