package database;

import business.logic.layer.Constants;
import business.logic.layer.Course;

import java.sql.*;

import static persistence.layer.mainScraper.getCourseListFromJSON;

public class Database {

    //creates database and populates it on user's system
    public static void formDatabase() throws Exception {

        // Opening a connection and creating database
        try (Connection conn = DriverManager.getConnection(Constants.CONNECTION1, Constants.USERNAME, Constants.PASSWORD);
             Statement stmt = conn.createStatement();
        ) {
            String sql = "CREATE DATABASE " + Constants.DATABASE;
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // creating table named courses
        try (Connection conn = DriverManager.getConnection(Constants.CONNECTION, Constants.USERNAME, Constants.PASSWORD);
             Statement stmt = conn.createStatement();
        ) {
            String sql = "CREATE TABLE courses (" +
                    "code VARCHAR(256), " +
                    "name TEXT(8000), " +
                    "description TEXT(8000), " +
                    "prerequisites TEXT(8000), " +
                    "PRIMARY KEY(code)" +
                    ");";
            stmt.executeUpdate(sql);
            System.out.println("Table courses was created successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //altering number of tablerows in courses to 1200
        try (Connection conn = DriverManager.getConnection(Constants.CONNECTION1, Constants.USERNAME, Constants.PASSWORD);
             Statement stmt = conn.createStatement();
        ) {
            String sql = "ALTER TABLE `yulookup`.`courses` " +
                    "MAX_ROWS = 1200 ";
            stmt.executeUpdate(sql);
            System.out.println("rows altered successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //populating database with courses
        for (int i = 0; i < getCourseListFromJSON().size(); i++) {
            //deleteCourse(getCourseListFromJSON().get(i).getCode());
            insertCourse(getCourseListFromJSON().get(i));
        }

        System.out.println("Total courses: " + getCourseListFromJSON().size());

    }

    /**
     * Inserts a course into the database
     **/
    public static boolean insertCourse(Course course) throws Exception {
        String sql = "INSERT INTO yulookup.courses (code, name, description, prerequisites) " + "VALUES (?,?,?,?);";
        ResultSet keys = null;
        try (
                Connection conn = DriverManager.getConnection(Constants.CONNECTION, Constants.USERNAME, Constants.PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql);

        ) {
            stmt.setString(1, course.getCode());
            stmt.setString(2, course.getName());
            stmt.setString(3, course.getDescription());
            stmt.setString(4, course.getPrerequisites().toString());
            int affected = stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            if (keys != null) {
                keys.close();
            }
        }
        return true;
    }

}
