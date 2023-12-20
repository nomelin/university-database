package nomelin.entity;

import nomelin.enums.Gender;

public record Student (String ID, String name, String gender, String year, String month, String classID){
    @Override
    public String toString() {
        return "学生基本信息{" +
                "学号='" + ID + '\'' +
                ", 姓名='" + name + '\'' +
                ", 性别='" + gender + '\'' +
                ", 出生年月='" + year +"-"+ month + '\'' +
                ", 所属班级='" + classID + '\'' +
                '}';
    }
}
