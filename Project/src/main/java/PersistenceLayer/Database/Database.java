package PersistenceLayer.Database;

import BusinessLogicLayer.Course;
import org.checkerframework.checker.units.qual.C;

import java.sql.*;

public class Database {
    private static final String username ="guest";
    private static final String connection="jdbc:mysql://localhost:3306/yulookup";
    //comment main method out later
    public static void main(String[] args) throws Exception {
        Courses course = new Courses();
        course.setCode("PHYS 2022");
        course.setName("Electromagnetism for Engineers");
        course.setDescription("software");
        course.setPrerequistes("EECS 2311,EECS 2321, PHYS 2930");

       // insertCourse(course);
        Courses course1 = getRow(course.getCode());
        if (course1 == null){
            System.err.println("Row not found");
            return;
        }
        course1.setPrerequistes("Hello");
        course1.setName("Im");
        course1.setDescription("dwumah");
        if (updateCourse(course1)){
            System.out.println("Succes!");
        }else{
            System.err.println("whoops!");
        }



    }

    /**
     * Provide the code of a Course and it will return the row in the database with
     * all its information if present
      **/
    public static Courses getRow(String code) throws SQLException {
        String sql = "SELECT * FROM courses WHERE code = ?";
        ResultSet rs = null;
        try(
                Connection conn = DriverManager.getConnection(connection, username, null);
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
    public static boolean insertCourse(Courses course) throws Exception {
        String sql = "INSERT INTO yulookup.courses (code, name, description, prerequisites) " + "VALUES (?,?,?,?);";
        ResultSet keys = null;
        try (
                Connection conn = DriverManager.getConnection(connection, username, null);
                PreparedStatement stmt = conn.prepareStatement(sql);
                //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ){
            stmt.setString(1,course.getCode());
            stmt.setString(2,course.getName());
            stmt.setString(3,course.getDescription());
            stmt.setString(4,course.getPrerequisites());
            int affected = stmt.executeUpdate();
//            //row was inserted into database
//            if (affected==1){
//               keys = stmt.getGeneratedKeys();
//               keys.next();// moving cursor to only row of data
//                int newKey =  keys.getInt(1);
//            }else{
//                System.err.println("No rows affected");
//                return false;
//            }
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
    public static boolean updateCourse(Courses course) throws Exception{
        String sql = "SELECT * FROM courses WHERE code = ?";
        ResultSet rs = null;
        try (
                Connection conn = DriverManager.getConnection(connection,username,null);
                PreparedStatement stmt = conn.prepareStatement(
                        sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ){
//            stmt.setString(1,course.getCode());
//            stmt.setString(2,course.getName());
//            stmt.setString(3,course.getDescription());
//            stmt.setString(4,course.getPrerequisites());
            stmt.setString(1,course.getCode());
            rs = stmt.executeQuery();
            if (rs.next()){
                rs.updateString("code",course.getCode());
                rs.updateString("name",course.getName());
                rs.updateString("description",course.getDescription());
                rs.updateString("prerequisites",course.getPrerequisites());
                rs.updateRow();
                return true;
            }else{
                return false;
            }


        }catch (SQLException e){
            System.err.println(e);
            return false;
        }finally {
            if (rs != null){
                rs.close();
            }
        }
    }
    /**
     * If given a valid course code it will find all instances of that course
     * in the database and delete them
     * **/
    public static boolean deleteCourse(String code) throws Exception{
        String sql = "DELETE FROM courses WHERE code = ?";
        try(
                Connection conn = DriverManager.getConnection(connection,username,null);
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

