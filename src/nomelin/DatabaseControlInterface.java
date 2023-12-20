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

    /**
     * 增加课程信息
     */
    void insertCourse(String courseID, String name, float credit);

    Course queryCourseByID(String courseID);

    // 学生信息管理

    /**
     * 增加学生信息
     */
    void insertStudent(String studentID, String name, String gender, String birthday, String classID);

    Student queryStudentByID(String studentID);

    List<Student> queryStudentByName(String name);



    /**
     * 通过专业号查询一个专业的学生
     */

    List<Student> queryStudentByMajorID(String majorID);

    /**
     * 通过专业名查询一个专业的学生
     */
    List<Student> queryStudentByMajorName(String majorName);

    /**
     * 通过学号查询学生专业
     */
    String queryMajorByStudent(String studentID);

    // 学生成绩管理

    /**
     * 增加成绩
     */
    void insertScore(String studentID, String courseID, String isMakeUp, double score);

    /**
     * 查询一位学生所修的课程、性质（必修或选修）、学期、学分及成绩；
     */
    List<CourseMessage> queryCoursesByStudentID(String studentID);

    /**
     * 必修课平均成绩
     */

    double queryAverageScoreForCompulsoryCourses(String studentID);

    /**
     * 平均成绩
     */

    double queryAverageScoreForAllCourses(String studentID);

    /**
     * 查询一个学生的所有老师
     */
    // 教师管理
    List<Teacher> queryTeachersForStudent(String studentID);

    /**
     * 增加教师信息
     */

    void insertTeacher(String teacherID, String name);

    /**
     * 由工号查询教师
     */

    Teacher queryTeacherByID(String teacherID);

    /**
     * 查询快开除的学生
     */
    // 学业管理
    List<Student> queryStudentsNearDismissal(int creditThreshold);


}