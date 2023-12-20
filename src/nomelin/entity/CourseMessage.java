package nomelin.entity;


public record CourseMessage (Course course,String type,String term,double score){
    @Override
    public String toString() {
        return "CourseMessage{" +
                "course=" + course +
                ", type='" + type + '\'' +
                ", term='" + term + '\'' +
                ", score=" + score +
                '}';
    }
}
