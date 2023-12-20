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
    void insertStudent(String studentID, String name, String gender, String birthday, String classID);

    Student queryStudentByID(String studentID);

    List<Student> queryStudentByName(String name);

    List<Student> queryStudentByMajorID(String majorID);
    List<Student> queryStudentByMajorName(String majorName);

    // 学生成绩管理
    void insertScore(String studentID, String courseName, String isMakeUp, double score);

    List<Course> queryCoursesByStudentID(String studentID);

    double queryAverageScoreForCompulsoryCourses(String studentID);

    double queryAverageScoreForAllCourses(String studentID);

    // 教师管理
    List<Teacher> queryTeachersForStudent(String studentID);

    // 学业管理
    List<Student> queryStudentsNearDismissal(int creditThreshold);
}