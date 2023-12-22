package nomelin;

import nomelin.entity.Course;
import nomelin.entity.Major;
import nomelin.entity.Student;
import nomelin.entity.Teacher;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseController db = new DatabaseController();
        db.connectDatabase();
        //loadFile(db);//载入文件
        Student s1 = db.queryStudentByID("03051018");
        System.out.println(s1);
        for (Student student : db.queryStudentByName("林丽娟")) {
            System.out.println(student);
        }
        /*System.out.println("010206");
        for(Student student:db.queryStudentByMajorID("010206")){
            System.out.println(student);
        }
        System.out.println("汉语言文学");
        for(Student student:db.queryStudentByMajorName("汉语言文学")){
            System.out.println(student);
        }*/
        Course c1 = db.queryCourseByID("CS1121006");
        System.out.println(c1);
        Major m1 = db.queryMajorByStudent("03051001");
        System.out.println(m1);
        m1 = db.queryMajorByStudent("03051002");
        System.out.println(m1);
        Teacher t1 = db.queryTeacherByID("178521");
        System.out.println(t1);
        System.out.printf("学生%s的平均学分成绩是%.2f\n", db.queryStudentByID("03051006").name(), db.queryAverageScoreForAllCourses("03051006"));


        db.disconnectDatabase();
    }

    private static void loadFile(DatabaseController db) {
        //注意外键，添加的顺序
        List<List<String>> lists = FileInput.readAndParseFile("src/resources/课程.txt");
        for (List<String> dataList : lists) {
            db.insertCourse(dataList.get(0), dataList.get(1), Float.parseFloat(dataList.get(2)));
        }
        lists = FileInput.readAndParseFile("src/resources/班级.txt");
        for (List<String> dataList : lists) {
            db.insertClass(dataList.get(0), dataList.get(1), dataList.get(2));
        }
        lists = FileInput.readAndParseFile("src/resources/学生.txt");
        for (List<String> dataList : lists) {
            db.insertStudent(dataList.get(0), dataList.get(1), dataList.get(2), dataList.get(3), dataList.get(4));
        }
        lists = FileInput.readAndParseFile("src/resources/成绩.txt");
        for (List<String> dataList : lists) {
            db.insertScore(dataList.get(0), dataList.get(1), "否", Double.parseDouble(dataList.get(2)));
        }
        lists = FileInput.readAndParseFile("src/resources/教师.txt");
        for (List<String> dataList : lists) {
            db.insertTeacher(dataList.get(0), dataList.get(1));
        }
        lists = FileInput.readAndParseFile("src/resources/教学计划.txt");
        for (List<String> dataList : lists) {
            db.insertPlan(dataList.get(0), dataList.get(1), dataList.get(2), dataList.get(3));
        }
        lists = FileInput.readAndParseFile("src/resources/教学.txt");
        for (List<String> dataList : lists) {
            db.insertTeach(dataList.get(0), dataList.get(1), dataList.get(2));
        }
    }
}