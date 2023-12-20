package nomelin;

import nomelin.entity.Course;
import nomelin.entity.CourseMessage;
import nomelin.entity.Student;
import nomelin.entity.Teacher;
import nomelin.enums.Gender;

import java.util.List;

public interface DatabaseControlInterface {
    // 数据库连接管理
    void connectDatabase();

    void disconnectDatabase();

    //课程管理
    void insertCourse(String courseID, String name, float credit);

    Course queryCourseByID(String courseID);

    // 学生信息管理
    void insertStudent(String studentID, String name, String gender, String birthday, String classID);

    Student queryStudentByID(String studentID);

    List<Student> queryStudentByName(String name);

    List<Student> queryStudentByMajorID(String majorID);

    List<Student> queryStudentByMajorName(String majorName);

    // 学生成绩管理
    void insertScore(String studentID, String courseID, String isMakeUp, double score);

    /**
     * 查询一位学生所修的课程、性质（必修或选修）、学期、学分及成绩；
     */
    List<CourseMessage> queryCoursesByStudentID(String studentID);

    double queryAverageScoreForCompulsoryCourses(String studentID);

    double queryAverageScoreForAllCourses(String studentID);

    // 教师管理
    List<Teacher> queryTeachersForStudent(String studentID);

    void insertTeacher(String teacherID, String name);

    Teacher queryTeacherByID(String teacherID);

    // 学业管理
    List<Student> queryStudentsNearDismissal(int creditThreshold);
}