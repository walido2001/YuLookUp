package PersistenceLayer.Database;

import BusinessLogicLayer.Course;
import org.checkerframework.checker.units.qual.C;

import java.sql.*;

public class Database {
    private static final String username ="guest";
    private static final String connection="jdbc:mariadb://localhost:3306/yulookup";

    public static void main(String[] args) throws Exception {
        Courses course = new Courses();
        course.setCode("PHYS 2020");
        course.setName("Electromagnetism for Engineers");
        course.setDescription("software");
        course.setPrerequistes("EECS 2311,EECS 2321, PHYS 2930");

        boolean result = insertCourse(course);
        if (result) {
            System.out.println("New row with primary code "+ course.getCode()+ " was inserted!");
        }


    }

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
    public static boolean insertCourse(Courses course) throws Exception {
        String sql = "INSERT INTO courses (code, name, description, prerequisites) " + "VALUES (?,?)";
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
//            int affected = stmt.executeUpdate();
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
}

