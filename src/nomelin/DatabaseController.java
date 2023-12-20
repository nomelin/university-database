package nomelin;

import nomelin.entity.Course;
import nomelin.entity.Student;
import nomelin.entity.Teacher;

import java.util.List;

public class DatabaseController implements DatabaseControlInterface{
    @Override
    public void connectToDatabase() {

    }

    @Override
    public void disconnectFromDatabase() {

    }

    @Override
    public void insertStudent(String studentID, String name, String gender, String birthday, String classID) {

    }

    @Override
    public Student queryStudentByID(String studentID) {
        return null;
    }

    @Override
    public List<Student> queryStudentByName(String name) {
        return null;
    }

    @Override
    public List<Student> queryStudentByMajorID(String majorID) {
        return null;
    }

    @Override
    public List<Student> queryStudentByMajorName(String majorName) {
        return null;
    }

    @Override
    public void insertScore(String studentID, String courseName, String isMakeUp, double score) {

    }

    @Override
    public List<Course> queryCoursesByStudentID(String studentID) {
        return null;
    }

    @Override
    public double queryAverageScoreForCompulsoryCourses(String studentID) {
        return 0;
    }

    @Override
    public double queryAverageScoreForAllCourses(String studentID) {
        return 0;
    }

    @Override
    public List<Teacher> queryTeachersForStudent(String studentID) {
        return null;
    }

    @Override
    public List<Student> queryStudentsNearDismissal(int creditThreshold) {
        return null;
    }
}
