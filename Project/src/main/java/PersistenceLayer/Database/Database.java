package PersistenceLayer.Database;

import BusinessLogicLayer.Course;
import PersistenceLayer.mainScraper;
import org.checkerframework.checker.units.qual.C;

import java.sql.*;
import java.util.ArrayList;

import static BusinessLogicLayer.courseSearchMethods.searchCourse;
import static PersistenceLayer.mainScraper.getCourseList;

public class Database {

    //change to YOUR username for you local mysql
    private static final String username ="root";

    //change to YOUR password for your local mysql
    private static final String password="";

    private static final String database="yulookup";
    private static final String connection1="jdbc:mysql://localhost:3306/";
    private static final String connection="jdbc:mysql://localhost:3306/"+database;

    //comment main method out later
    public static void main(String[] args) throws Exception {

        // Opening a connection and creating database
        try(Connection conn = DriverManager.getConnection(connection1, username, password);
            Statement stmt = conn.createStatement();
        ) {
            String sql = "CREATE DATABASE "+database;
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // creating table named courses
        try(Connection conn = DriverManager.getConnection(connection, username, password);
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
        try(Connection conn = DriverManager.getConnection(connection1, username, password);
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
        for (int i=0; i< getCourseList().size();i++){
            //deleteCourse(getCourseList().get(i).getCode());
            insertCourse(getCourseList().get(i));
        }

        System.out.println("Total courses: "+getCourseList().size());





    }

    /**
     * Provide the code of a Course and it will return the row in the database with
     * all its information if present
      **/
    public static Courses getRow(String code) throws SQLException {
        String sql = "SELECT * FROM courses WHERE code = ?";
        ResultSet rs = null;
        try(
                Connection conn = DriverManager.getConnection(connection, username, password);
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setString(1,code);
            rs = stmt.executeQuery();

            if (rs.next()){
               Courses course = new Courses();
               course.setCode(code);
               course.setName(rs.getString("name"));
               course.setDescription(rs.getString("description"));
               course.setPrerequistes(rs.getString("prerequisites"));
               return course;
            }else{
                System.err.println("No rows were found");
                return null;
            }

        }catch (SQLException e){
            System.err.println(e);
            return null;
        }finally{
            if (rs!= null){
                rs.close();
            }
        }
    }
    /**
     * Inserts a course into the database
     * **/
    public static boolean insertCourse(Course course) throws Exception {
        String sql = "INSERT INTO yulookup.courses (code, name, description, prerequisites) " + "VALUES (?,?,?,?);";
        ResultSet keys = null;
        try (
                Connection conn = DriverManager.getConnection(connection, username, password);
                PreparedStatement stmt = conn.prepareStatement(sql);

        ){
            stmt.setString(1,course.getCode());
            stmt.setString(2,course.getName());
            stmt.setString(3,course.getDescription());
            stmt.setString(4,course.getPrerequisites().toString());
            int affected = stmt.executeUpdate();

        }
        catch (SQLException e) {
            System.err.println(e);
            return false;
        }finally{
            if (keys != null) {
                keys.close();
            }
        }
        return true;
    }
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
    public static boolean deleteCourse(String code) throws Exception{
        String sql = "DELETE FROM courses WHERE code = ?";
        try(
                Connection conn = DriverManager.getConnection(connection,username,password);
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setString(1,code);
            int affected = stmt.executeUpdate();
            if (affected ==1){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            System.err.println(e);
            return false;
        }
    }

}

