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
    public void insertStudent(String studentID, String name, String gender, String birthday, String className) {

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
    public List<Student> queryStudentByMajor(String major) {
        return null;
    }

    @Override
    public void insertScore(String studentID, String courseName, String semester, double score) {

    }

    @Override
    public List<Course> queryCoursesTakenByStudent(String studentID) {
        return null;
    }

    @Override
    public double queryAverageScoreForCompulsoryCourses(String studentID) {
        return 0;
    }

    @Override
    public double queryWeightedAverageScoreForAllCourses(String studentID) {
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
