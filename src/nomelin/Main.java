package nomelin;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        DatabaseController db = new DatabaseController();
        db.connectDatabase();
        db.insertCourse('');

        db.disconnectDatabase();
    }
}