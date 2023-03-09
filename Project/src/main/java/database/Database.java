package database;

import business.logic.layer.Constants;
import business.logic.layer.Course;

import java.sql.*;

import static persistence.layer.mainScraper.getCourseListFromJSON;

public class Database {

    //comment main method out later
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
    /**
     * Provide the code of a Course and it will return the row in the database with
     * all its information if present
      **/
//    public static Courses getRow(String code) throws SQLException {
//        String sql = "SELECT * FROM courses WHERE code = ?";
//        ResultSet rs = null;
//        try(
//                Connection conn = DriverManager.getConnection(connection, username, password);
//                PreparedStatement stmt = conn.prepareStatement(sql);
//                ){
//            stmt.setString(1,code);
//            rs = stmt.executeQuery();
//
//            if (rs.next()){
//               Courses course = new Courses();
//               course.setCode(code);
//               course.setName(rs.getString("name"));
//               course.setDescription(rs.getString("description"));
//               course.setPrerequistes(rs.getString("prerequisites"));
//               return course;
//            }else{
//                System.err.println("No rows were found");
//                return null;
//            }
//
//        }catch (SQLException e){
//            System.err.println(e);
//            return null;
//        }finally{
//            if (rs!= null){
//                rs.close();
//            }
//        }
//    }


    /**
     * updates the course in the database with the same course code
     * with new values for the decription, prerequisites and name
     * NOT THE COURSE code
     * **/

//    public static boolean updateCourse(Course course) throws Exception{
//        String sql = "SELECT * FROM courses WHERE code = ?";
//        ResultSet rs = null;
//        try (
//                Connection conn = DriverManager.getConnection(connection,username,password);
//                PreparedStatement stmt = conn.prepareStatement(
//                        sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//                ){
//
//            stmt.setString(1,course.getCode());
//            rs = stmt.executeQuery();
//            if (rs.next()){
//                rs.updateString("code",course.getCode());
//                rs.updateString("name",course.getName());
//                rs.updateString("description",course.getDescription());
//                rs.updateString("prerequisites",course.getPrerequisites().toString());
//                rs.updateRow();
//                return true;
//            }else{
//                return false;
//            }
//
//
//        }catch (SQLException e){
//            System.err.println(e);
//            return false;
//        }finally {
//            if (rs != null){
//                rs.close();
//            }
//        }
//    }
    /**
     * If given a valid course code it will find all instances of that course
     * in the database and delete them
     * **/
//    public static boolean deleteCourse(String code) throws Exception{
//        String sql = "DELETE FROM courses WHERE code = ?";
//        try(
//                Connection conn = DriverManager.getConnection(connection,username,password);
//                PreparedStatement stmt = conn.prepareStatement(sql);
//                ){
//            stmt.setString(1,code);
//            int affected = stmt.executeUpdate();
//            if (affected ==1){
//                return true;
//            }else{
//                return false;
//            }
//        }catch (SQLException e){
//            System.err.println(e);
//            return false;
//        }
//    }



//package PersistenceLayer.Database;
//
//import java.io.Serializable;
//
//public class Courses {
//    private String code;
//    private String name;
//    private String description;
//    private String prerequisites;
//
//    public Courses() {
//        code = null;
//        name = null;
//        description = null;
//        prerequisites = null;
//    }
//
//    public String getPrerequisites() {
//        return prerequisites;
//    }
//    public void setPrerequistes(String prerequistes) {
//        this.prerequisites = prerequistes;
//    }
//    public String getDescription() {
//        return description;
//    }
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//}

