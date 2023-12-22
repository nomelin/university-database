package nomelin.entity;


public record CourseMessage (Course course,String type,String term,double score){
    @Override
    public String toString() {
        return "学生选课详细信息{" +
                "课程=" + course +
                ", 性质='" + type + '\'' +
                ", 授课学期='" + term + '\'' +
                ", 成绩=" + score +
                '}';
    }
}
