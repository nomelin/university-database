package nomelin.entity;

public record Major(String ID, String name) {
    @Override
    public String toString() {
        return "专业信息{" +
                "专业号='" + ID + '\'' +
                ", 专业名='" + name + '\'' +
                '}';
    }
}
