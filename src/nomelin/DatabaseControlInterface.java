package nomelin;

import nomelin.entity.Course;
import nomelin.entity.Student;
import nomelin.entity.Teacher;

import java.util.List;

public interface DatabaseControlInterface {
    // 数据库连接管理
    void connectToDatabase();
    void disconnectFromDatabase();

    // 学生信息管理
    void insertStudent(String studentID, String name, String gender, String birthday, String className);
    Student queryStudentByID(String studentID);
    List<Student> queryStudentByName(String name);
    List<Student> queryStudentByMajor(String major);

    // 学生成绩管理
    void insertScore(String studentID, String courseName, String semester, double score);
    List<Course> queryCoursesTakenByStudent(String studentID);
    double queryAverageScoreForCompulsoryCourses(String studentID);
    double queryWeightedAverageScoreForAllCourses(String studentID);

    // 教师管理
    List<Teacher> queryTeachersForStudent(String studentID);

    // 学业管理
    List<Student> queryStudentsNearDismissal(int creditThreshold);
}