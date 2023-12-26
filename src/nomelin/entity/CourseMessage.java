package nomelin.entity;


public record CourseMessage (Course course,String type,String term,String isMakeUp,double score){
    @Override
    public String toString() {
        return "学生选课详细信息{" +
                "课程=" + course +
                ", 性质='" + type + '\'' +
                ", 授课学期='" + term + '\'' +
                ", 是否补考='" + isMakeUp + '\'' +
                ", 成绩=" + score +
                '}';
    }
}
