package nomelin;

import nomelin.entity.Course;
import nomelin.entity.CourseMessage;
import nomelin.entity.Student;
import nomelin.entity.Teacher;

import java.util.List;
import java.util.Scanner;

public class UI {
    private static final DatabaseController DB = new DatabaseController();
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main() {
        DB.connectDatabase();
        System.out.println("\u001B[34m[UI][主菜单]欢迎来到学籍管理数据库系统\u001B[0m");
        while (true) {
            System.out.println("""
                    \u001B[36m
                    [UI][主菜单]
                    1.信息录入系统
                    2.信息查询系统
                    3.退出数据库系统
                    \u001B[0m
                    """);
            System.out.print("[UI][主菜单]请输入：");
            int mode = readInt();
            if (mode == 1) {
                while (true) {
                    System.out.println("""
                            [UI][录入菜单]
                            1.录入学生信息
                            2.录入教师信息
                            3.录入专业教学计划表
                            4.录入教师教学安排
                            5.录入学生成绩
                            6.录入新班级
                            7.录入新课程
                            8.返回主菜单
                            9.退出系统
                            """);
                    System.out.print("[UI][录入菜单]请输入：");
                    mode = readInt();
                    if (mode == 1) {
                        System.out.println("[UI][录入菜单]请输入学生信息：");
                        System.out.print("学号：");
                        String studentID = readLine();
                        System.out.print("姓名：");
                        String name = readLine();
                        System.out.print("性别（男/女）：");
                        String gender = readLine();
                        System.out.print("出生年月（年-月）：");
                        String birthday = readLine();
                        System.out.print("班号：");
                        String classID = readLine();
                        DB.insertStudent(studentID, name, gender, birthday, classID);

                    } else if (mode == 2) {
                        System.out.println("[UI][录入菜单]请输入教师信息：");
                        System.out.print("工号：");
                        String teacherId = readLine();
                        System.out.print("姓名：");
                        String name = readLine();
                        DB.insertTeacher(teacherId, name);
                    } else if (mode == 3) {
                        System.out.println("[UI][录入菜单]请输入专业教学计划：");
                        System.out.print("专业号：");
                        String majorID = readLine();
                        System.out.print("课程号：");
                        String courseID = readLine();
                        System.out.print("性质（必修/选修）：");
                        String type = readLine();
                        System.out.print("授课学期（大一上、大一下...大四下）：");
                        String term = readLine();
                        DB.insertPlan(majorID, courseID, type, term);
                    } else if (mode == 4) {
                        System.out.println("[UI][录入菜单]请输入教师教学安排：");
                        System.out.print("班号：");
                        String classID = readLine();
                        System.out.print("课程号：");
                        String courseID = readLine();
                        System.out.print("工号：");
                        String teacherID = readLine();
                        DB.insertTeach(classID, courseID, teacherID);
                    } else if (mode == 5) {
                        System.out.println("[UI][录入菜单]请输入学生成绩：");
                        System.out.print("学号：");
                        String studentID = readLine();
                        System.out.print("课程号：");
                        String courseID = readLine();
                        System.out.print("是否补考（是/否）：");
                        String isMakeUp = readLine();
                        System.out.print("成绩：");
                        double score = readDouble();
                        DB.insertScore(studentID, courseID, isMakeUp, score);
                    } else if (mode == 6) {
                        System.out.println("[UI][录入菜单]请输入班级信息：");
                        System.out.print("班号：");
                        String classID = readLine();
                        System.out.print("入学年份：");
                        String year = readLine();
                        System.out.print("专业号：");
                        String majorID = readLine();
                        DB.insertClass(classID, year, majorID);
                    } else if (mode == 7) {
                        System.out.println("[UI][录入菜单]请输入课程信息：");
                        System.out.print("课程号：");
                        String courseID = readLine();
                        System.out.print("课程名：");
                        String name = readLine();
                        System.out.print("学分：");
                        float credit = readFloat();
                        DB.insertCourse(courseID, name, credit);
                    } else if (mode == 8) {
                        break; // 返回主菜单
                    } else if (mode == 9) {
                        System.out.println("[UI][录入菜单]退出中...");
                        close();
                        return; // 退出系统
                    } else {
                        System.out.println("[UI][录入菜单]输入错误，请重新输入");
                    }
                }
            } else if (mode == 2) {
                while (true) {
                    System.out.println("""
                            [UI][查询菜单]
                            1.查询学生基本信息
                            2.查询学生所修课程信息
                            3.查询学生所有课程平均学分成绩
                            4.查询学生必修课平均学分成绩
                            5.查询学生教师信息
                            6.查询临近开除学生
                            7.返回主菜单
                            8.退出系统
                            """);
                    System.out.print("[UI][查询菜单]请输入：");
                    mode = readInt();
                    if (mode == 1) {
                        while (true) {
                            System.out.println("""
                                    [UI][查询学生基本信息菜单]
                                    1.通过学号查询
                                    2.通过姓名查询
                                    3.通过专业号查询
                                    4.通过专业名查询
                                    5.返回查询菜单
                                    """);
                            System.out.print("[UI][查询学生基本信息菜单]请输入：");
                            mode = readInt();
                            if (mode == 1) {
                                System.out.println("[UI][查询学生基本信息菜单]请输入要查询的学号：");
                                String studentID = readLine();
                                Student student = DB.queryStudentByID(studentID);
                                if (student != null) {
                                    System.out.println(student);
                                } else {
                                    System.out.println("找不到学号为" + studentID + "的学生信息");
                                }
                            } else if (mode == 2) {
                                System.out.println("[UI][查询学生基本信息菜单]请输入要查询的姓名：");
                                String name = readLine();
                                List<Student> students = DB.queryStudentByName(name);
                                if (!students.isEmpty()) {
                                    for (Student student : students) {
                                        System.out.println(student.toString());
                                    }
                                } else {
                                    System.out.println("找不到姓名为" + name + "的学生信息");
                                }
                            } else if (mode == 3) {
                                System.out.println("[UI][查询学生基本信息菜单]请输入要查询的专业号：");
                                String majorID = readLine();
                                List<Student> students = DB.queryStudentByMajorID(majorID);
                                if (!students.isEmpty()) {
                                    for (Student student : students) {
                                        System.out.println(student.toString());
                                    }
                                } else {
                                    System.out.println("找不到专业号为" + majorID + "的学生信息");
                                }
                            } else if (mode == 4) {
                                System.out.println("[UI][查询学生基本信息菜单]请输入要查询的专业名：");
                                String majorName = readLine();
                                List<Student> students = DB.queryStudentByMajorName(majorName);
                                if (!students.isEmpty()) {
                                    for (Student student : students) {
                                        System.out.println(student.toString());
                                    }
                                } else {
                                    System.out.println("找不到专业名为" + majorName + "的学生信息");
                                }
                            } else if (mode == 5) {
                                break;
                            } else {
                                System.out.println("[UI][查询学生基本信息菜单]输入错误，请重新输入");
                            }
                        }
                    } else if (mode == 2) {
                        System.out.println("[UI][查询菜单]请输入学号：");
                        String studentID = readLine();
                        List<CourseMessage> courses = DB.queryCoursesByStudentID(studentID);
                        if (!courses.isEmpty()) {
                            for (CourseMessage course : courses) {
                                System.out.println(course.toString());
                            }
                        } else {
                            System.out.println("找不到学号为" + studentID + "的学生所修课程信息");
                        }
                    } else if (mode == 3) {
                        System.out.println("[UI][查询菜单]请输入学号：");
                        String studentID = readLine();
                        double averageScore = DB.queryAverageScoreForAllCourses(studentID);
                        System.out.println("学号为" + studentID + "的学生所有课程平均学分成绩为：" + averageScore);
                    } else if (mode == 4) {
                        System.out.println("[UI][查询菜单]请输入学号：");
                        String studentID = readLine();
                        double averageScore = DB.queryAverageScoreForCompulsoryCourses(studentID);
                        System.out.println("学号为" + studentID + "的学生必修课平均学分成绩为：" + averageScore);
                    } else if (mode == 5) {
                        System.out.println("[UI][查询菜单]请输入学号：");
                        String studentID = readLine();
                        List<Teacher> teachers = DB.queryTeachersForStudent(studentID);
                        if (!teachers.isEmpty()) {
                            for (Teacher teacher : teachers) {
                                Course course = DB.queryCourseByStudentAndTeacher(studentID, teacher.ID());
                                System.out.println(teacher.toString() + "；教授课程为：" + course);
                            }
                        } else {
                            System.out.println("找不到学号为" + studentID + "的学生的教师信息");
                        }
                    } else if (mode == 6) {
                        System.out.println("[UI][查询菜单]请输入距被开除差的学分：");
                        float value = readFloat();
                        List<Student> nearDismissalStudents = DB.queryStudentsNearDismissal(value);
                        if (!nearDismissalStudents.isEmpty()) {
                            for (Student student : nearDismissalStudents) {
                                System.out.println(student.toString());
                            }
                        } else {
                            System.out.println("找不到临近开除学生");
                        }
                    } else if (mode == 7) {
                        break;
                    } else if (mode == 8) {
                        System.out.println("[UI][查询菜单]退出中...");
                        close();
                        return; // 退出系统
                    } else {
                        System.out.println("[UI][查询菜单]输入错误，请重新输入");
                    }
                }
            } else if (mode == 3) {
                System.out.println("[UI][主菜单]退出中...");
                close();
                return;
            } else {
                System.out.println("[UI][主菜单]输入错误，请重新输入");
            }
        }
    }

    private static void close() {
        DB.disconnectDatabase();
    }

    private static int readInt() {
        int num = SCANNER.nextInt();
        SCANNER.nextLine(); // 清除缓冲区
        return num;
    }

    private static float readFloat() {
        float num = SCANNER.nextFloat();
        SCANNER.nextLine(); // 清除缓冲区
        return num;
    }

    private static double readDouble() {
        double num = SCANNER.nextDouble();
        SCANNER.nextLine(); // 清除缓冲区
        return num;
    }

    public static String readLine() {
        return SCANNER.nextLine();
    }


}
