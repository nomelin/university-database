package nomelin.entity;

public record Class(String classID, String majorID, int year) {
    @Override
    public String toString() {
        return "班级信息{" +
                "班号='" + classID + '\'' +
                ", 专业号='" + majorID + '\'' +
                ", 入学年份=" + year +
                '}';
    }
}
