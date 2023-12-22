package nomelin.entity;

public record Teacher(String ID, String name) {
    @Override
    public String toString() {
        return "教师信息{" +
                "工号='" + ID + '\'' +
                ", 姓名='" + name + '\'' +
                '}';
    }
}
