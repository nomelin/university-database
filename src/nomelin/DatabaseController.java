package nomelin;

import nomelin.entity.*;
import nomelin.enums.Type;

import java.lang.Class;
import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author nomelin
 */
public class DatabaseController implements DatabaseControlInterface {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/学籍管理系统";
    private static final String DB_NAME = "root";
    private static final String DB_PASSWORD = "123456";
    private Connection con = null;
    private PreparedStatement pStmt = null;
    //private Statement stmt = null;
    private ResultSet rs = null;


    @Override
    public void connectDatabase() {
        try {
            //注册驱动
            Class.forName(JDBC_DRIVER);
            //获取数据库的连接对象
            con = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
            System.out.println("[DB]成功连接到数据库");
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
        /*
        if (stmt != null) {
            //7、释放资源
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
        if (rs != null) {
            //7、释放资源
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[DB]成功从数据库断开连接");
    }

    @Override
    public void insertStudent(String studentID, String name, String gender, String birthday, String classID) {
        try {
            // 将字符串形式的日期转换为Date对象
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM");
            java.util.Date utilDate = inputFormat.parse(birthday);
            Date sqlDate = new Date(utilDate.getTime());
            String query = "REPLACE INTO 学生 (学号, 姓名, 性别, 出生年月, 班号) VALUES (?, ?, ?, ?, ?)";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, studentID);
            pStmt.setString(2, name);
            pStmt.setString(3, gender);
            pStmt.setDate(4, sqlDate);
            pStmt.setString(5, classID);
            pStmt.executeUpdate();
            System.out.println("[DB]添加学生成功：" + studentID + " " + name + " " + gender + " " + birthday + " " + classID);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
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
            String query = "REPLACE INTO 课程 (课程号, 课程名, 学分) VALUES (?, ?, ?)";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, courseID);
            pStmt.setString(2, name);
            pStmt.setBigDecimal(3, BigDecimal.valueOf(credit));
            pStmt.executeUpdate();
            System.out.println("[DB]插入课程成功：" + courseID + " " + name + " " + credit);
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
            String query = "REPLACE INTO 教师 (工号, 姓名) VALUES (?, ?)";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, teacherID);
            pStmt.setString(2, name);
            pStmt.executeUpdate();
            System.out.println("[DB]添加教师成功：" + teacherID + " " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Major queryMajorByStudent(String studentID) {
        Major major = null;
        try {
            String query = "SELECT 专业.* " +
                    "FROM 专业 " +
                    "INNER JOIN 班级 ON 专业.专业号 = 班级.专业号 " +
                    "INNER JOIN 学生 ON 班级.班号 = 学生.班号 " +
                    "WHERE 学生.学号 = ?";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, studentID);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                major = new Major(
                        rs.getString("专业号"),
                        rs.getString("专业名")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return major;
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
    public void insertClass(String classID, String year, String majorID) {
        try {
            String query = "REPLACE INTO 班级 (班号, 入学年份, 专业号) VALUES (?, ?, ?)";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, classID);
            pStmt.setString(2, year);
            pStmt.setString(3, majorID);
            pStmt.executeUpdate();
            System.out.println("[DB]插入班级成功：" + classID + " " + year + " " + majorID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> queryStudentByMajorID(String majorID) {
        return queryStudentByMajorName(queryMajorByID(majorID).name());
    }

    @Override
    public List<Student> queryStudentByMajorName(String majorName) {
        List<Student> students = new ArrayList<>();
        try {
            String query = "SELECT 学生.* " +
                    "FROM 学生 " +
                    "INNER JOIN 班级 ON 学生.班号 = 班级.班号 " +
                    "INNER JOIN 专业 ON 班级.专业号 = 专业.专业号 " +
                    "WHERE 专业.专业名 = ?";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, majorName);
            rs = pStmt.executeQuery();
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
    public void insertScore(String studentID, String courseID, String isMakeUp, double score) {
        try {
            String query = "REPLACE INTO 成绩 (学号, 课程号, 是否补考, 成绩) VALUES (?, ?, ?, ?)";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, studentID);
            pStmt.setString(2, courseID);
            pStmt.setString(3, isMakeUp);
            pStmt.setBigDecimal(4, BigDecimal.valueOf(score));
            pStmt.executeUpdate();
            System.out.println("[DB]添加分数成功：" + studentID + " " + courseID + " " + isMakeUp + " " + score);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<CourseMessage> queryCoursesByStudentID(String studentID) {
        nomelin.entity.Class class1 = queryClassByStudentID(studentID);
        List<CourseMessage> courseMessages = new ArrayList<>();
        try {
            String query = "SELECT * " +
                    "FROM 课程 " +
                    "INNER JOIN 教学计划 ON 课程.课程号 = 教学计划.课程号 " +
                    "INNER JOIN 成绩 ON 教学计划.课程号 = 成绩.课程号 " +
                    "WHERE 教学计划.专业号 = ? AND 成绩.学号 = ? ";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, class1.majorID());
            pStmt.setString(2, studentID);
            //System.out.println(class1.majorID()+" "+studentID);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                CourseMessage courseMessage = new CourseMessage(
                        new Course(
                                rs.getString("课程号"),
                                rs.getString("课程名"),
                                rs.getFloat("学分")
                        ),
                        rs.getString("性质"),
                        rs.getString("授课学期"),
                        rs.getString("是否补考"),
                        rs.getDouble("成绩")
                );
                courseMessages.add(courseMessage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseMessages;
    }

    @Override
    public double queryAverageScoreForCompulsoryCourses(String studentID) {
        List<CourseMessage> courseMessages = queryCoursesByStudentID(studentID);
        double sum = 0;
        double creditSum = 0;
        for (CourseMessage courseMessage : courseMessages) {
            if (courseMessage.type().equals(Type.O.type())) {
                continue;
            }
            creditSum += courseMessage.course().credit();
            sum += courseMessage.score() * courseMessage.course().credit();
        }
        if (creditSum == 0.0) {
            return 0;
        }
        return sum / creditSum;
    }

    @Override
    public double queryAverageScoreForAllCourses(String studentID) {
        List<CourseMessage> courseMessages = queryCoursesByStudentID(studentID);
        double sum = 0;
        double creditSum = 0;
        for (CourseMessage courseMessage : courseMessages) {
            creditSum += courseMessage.course().credit();
            sum += courseMessage.score() * courseMessage.course().credit();
        }
        if (creditSum == 0.0) {
            return 0;
        }
        return sum / creditSum;
    }

    @Override
    public List<Teacher> queryTeachersForStudent(String studentID) {
        List<Teacher> teachers = new ArrayList<>();
        try {
            String query = "SELECT 教师.* " +
                    "FROM 教师 " +
                    "INNER JOIN 教学 ON 教师.工号 = 教学.工号 " +
                    "INNER JOIN 班级 ON 教学.班号 = 班级.班号 " +
                    "INNER JOIN 学生 ON 班级.班号 = 学生.班号 " +
                    "WHERE 学号 = ?";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, studentID);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                Teacher teacher = new Teacher(
                        rs.getString("工号"),
                        rs.getString("姓名")
                );
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }


    @Override
    public List<Student> queryStudentsNearDismissal(float value) {
        List<String> studentIDs = new ArrayList<>();
        try {
            String query = "SELECT 学号 " +
                    "FROM 学生 ";
            pStmt = con.prepareStatement(query);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                studentIDs.add(rs.getString("学号"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Student> students = new ArrayList<>();
        for (String ID : studentIDs) {
            float compulsoryCredit = 0;//必修不及格学分
            float optionalCredit = 0;//选修不及格学分

            List<CourseMessage> courseMessages = queryCoursesByStudentID(ID);
            for (CourseMessage courseMessage : courseMessages) {
                if (courseMessage.type().equals(Type.C.type())) {
                    if (courseMessage.score() < 60.0) {
                        compulsoryCredit += courseMessage.course().credit();
                    }
                } else if (courseMessage.type().equals(Type.O.type())) {
                    if (courseMessage.score() < 60.0) {
                        optionalCredit += courseMessage.course().credit();
                    }
                }
            }
            if (compulsoryCredit >= 10 - value || optionalCredit >= 15 - value) {
                students.add(queryStudentByID(ID));
            }
        }
        return students;
    }

    @Override
    public void insertPlan(String majorID, String courseID, String type, String term) {
        try {
            String query = "REPLACE INTO 教学计划 (专业号, 课程号, 性质, 授课学期) VALUES (?, ?, ?, ?)";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, majorID);
            pStmt.setString(2, courseID);
            pStmt.setString(3, type);
            pStmt.setString(4, term);
            pStmt.executeUpdate();
            System.out.println("[DB]增加教学计划成功：" + majorID + " " + courseID + " " + type + " " + term);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertTeach(String classID, String courseID, String teacherID) {
        try {
            String query = "REPLACE INTO 教学 (班号, 课程号, 工号) VALUES (?, ?, ?)";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, classID);
            pStmt.setString(2, courseID);
            pStmt.setString(3, teacherID);
            pStmt.executeUpdate();
            System.out.println("[DB]增加教学表成功：" + classID + " " + courseID + " " + teacherID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Major queryMajorByID(String majorID) {
        Major major = null;
        try {
            String query = "SELECT 专业.* " +
                    "FROM 专业 " +
                    "WHERE 专业.专业号 = ?";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, majorID);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                major = new Major(
                        rs.getString("专业号"),
                        rs.getString("专业名")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return major;
    }

    @Override
    public nomelin.entity.Class queryClassByStudentID(String studentID) {
        nomelin.entity.Class class1 = null;
        try {
            String query = "SELECT 班级.* " +
                    "FROM 班级 " +
                    "INNER JOIN 学生 ON 学生.班号 = 班级.班号 " +
                    "WHERE 学生.学号 = ?";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, studentID);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                class1 = new nomelin.entity.Class(
                        rs.getString("班号"),
                        rs.getString("专业号"),
                        rs.getInt("入学年份")
                );
                return class1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return class1;
    }

    @Override
    public Course queryCourseByStudentAndTeacher(String studentID, String teacherID) {
        Course course = null;
        try {
            String query = "SELECT 课程.* " +
                    "FROM 课程 " +
                    "INNER JOIN 教学 ON 课程.课程号 = 教学.课程号 " +
                    "INNER JOIN 班级 ON 教学.班号 = 班级.班号 " +
                    "INNER JOIN 学生 ON 班级.班号 = 学生.班号 " +
                    "INNER JOIN 教师 ON 教学.工号 = 教师.工号 " +
                    "WHERE 学号 = ? AND 教学.工号=?";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, studentID);
            pStmt.setString(2, teacherID);
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
}
