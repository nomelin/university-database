package nomelin;

import nomelin.entity.Course;
import nomelin.entity.CourseMessage;
import nomelin.entity.Student;
import nomelin.entity.Teacher;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController implements DatabaseControlInterface {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/学籍管理系统";
    private static final String DB_NAME = "root";
    private static final String DB_PASSWORD = "123456";
    private Connection con = null;
    private PreparedStatement pStmt = null;
    private Statement stmt = null;
    private ResultSet rs = null;


    @Override
    public void connectDatabase() {
        try {
            //注册驱动
            Class.forName(JDBC_DRIVER);
            //获取数据库的连接对象
            con = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnectDatabase() {
        //避免空指针异常
        if (con != null) {
            //7、释放资源
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pStmt != null) {
            //7、释放资源
            try {
                pStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            //7、释放资源
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            //7、释放资源
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insertStudent(String studentID, String name, String gender, String birthday, String classID) {
        try {
            String query = "INSERT INTO 学生 (学号, 姓名, 性别, 出生年月, 班号) VALUES (?, ?, ?, ?, ?)";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, studentID);
            pStmt.setString(2, name);
            pStmt.setString(3, gender);
            pStmt.setString(4, birthday);
            pStmt.setString(5, classID);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student queryStudentByID(String studentID) {
        Student student = null;
        try {
            String query = "SELECT * FROM 学生 WHERE 学号 = ?";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, studentID);
            rs = pStmt.executeQuery();
            //只有一个学生
            if (rs.next()) {
                Date birthDate = rs.getDate("出生年月"); // 获取出生年月的Date对象
                SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
                SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
                String year = yearFormat.format(birthDate); // 格式化年份
                String month = monthFormat.format(birthDate); // 格式化月份

                student = new Student(
                        rs.getString("学号"),
                        rs.getString("姓名"),
                        rs.getString("性别"),
                        year,
                        month,
                        rs.getString("班号")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> queryStudentByName(String name) {
        List<Student> students = new ArrayList<>();
        try {
            String query = "SELECT * FROM 学生 WHERE 姓名 = ?";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, name);
            rs = pStmt.executeQuery();
            //可能有多个学生
            while (rs.next()) {
                Date birthDate = rs.getDate("出生年月"); // 获取出生年月的Date对象
                SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
                SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
                String year = yearFormat.format(birthDate); // 格式化年份
                String month = monthFormat.format(birthDate); // 格式化月份

                Student student = new Student(
                        rs.getString("学号"),
                        rs.getString("姓名"),
                        rs.getString("性别"),
                        year,
                        month,
                        rs.getString("班号")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    @Override
    public void insertCourse(String courseID, String name, float credit) {
        try {
            String query = "INSERT INTO 课程 (课程号, 课程名, 学分) VALUES (?, ?, ?)";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, courseID);
            pStmt.setString(2, name);
            pStmt.setBigDecimal(3,BigDecimal.valueOf(credit));
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Course queryCourseByID(String courseID) {
        Course course = null;
        try {
            String query = "SELECT * FROM 课程 WHERE 课程号 = ?";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, courseID);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                course = new Course(
                        rs.getString("课程号"),
                        rs.getString("课程名"),
                        rs.getFloat("学分")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public void insertTeacher(String teacherID, String name) {
        try {
            String query = "INSERT INTO 教师 (工号, 姓名) VALUES (?, ?)";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, teacherID);
            pStmt.setString(2, name);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Teacher queryTeacherByID(String teacherID) {
        Teacher teacher = null;
        try {
            String query = "SELECT * FROM 教师 WHERE 工号 = ?";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, teacherID);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                teacher = new Teacher(
                        rs.getString("工号"),
                        rs.getString("姓名")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
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
    public void insertScore(String studentID, String courseID, String isMakeUp, double score) {
        try {
            String query = "INSERT INTO 成绩 (学号, 课程号, 是否补考, 成绩) VALUES (?, ?, ?, ?)";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, studentID);
            pStmt.setString(2, courseID);
            pStmt.setString(3, isMakeUp);
            pStmt.setBigDecimal(4, BigDecimal.valueOf(score));
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<CourseMessage> queryCoursesByStudentID(String studentID) {
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
