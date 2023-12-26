package nomelin;

import nomelin.entity.*;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UI.main();
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