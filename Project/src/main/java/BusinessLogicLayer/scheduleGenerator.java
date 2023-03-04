package BusinessLogicLayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;

import static PersistenceLayer.mainScraper.getCourseList;

public class scheduleGenerator {

    static String[] firstYear;
    static String[] secondYear;
    static String[] thirdYear;
    static String[] fourthYear;

    static Major mechMajor = new Major("Mechanical Engineering");
    public static LinkedHashMap<Integer, ArrayList<Course>> mechCourses = mechMajor.getMajor();
    static Major compMajor = new Major("Computer Engineering");
    public static LinkedHashMap<Integer, ArrayList<Course>> compCourses = compMajor.getMajor();
    static Major civilMajor = new Major("Civil Engineering");
    public static LinkedHashMap<Integer, ArrayList<Course>> civilCourses = civilMajor.getMajor();
    static Major elecMajor = new Major("Electrical Engineering");
    public static LinkedHashMap<Integer, ArrayList<Course>> elecCourses = elecMajor.getMajor();

    public static void main(String[] args) {

        ArrayList<Course> courses = getCourseList();

        setMajor("mechanical");

    }

    static void setMajor(String major) {

        LinkedHashMap<Integer,ArrayList<Course>> majorCourses = null;

            switch(major) {
                case "civil":
                    majorCourses = civilCourses;
                    break;

                case "mechanical":
                    majorCourses = mechCourses;
                    break;

                case "electrical":
                    majorCourses = elecCourses;
                    break;

                case "computer":
                    majorCourses = compCourses;
                    break;

                default:
                    break;
            }

        for(int i=0; i<majorCourses.get(1).size(); i++) {
            firstYear = new String[majorCourses.get(1).size()];
            firstYear[i] = String.valueOf(majorCourses.get(1).get(i));
            System.out.println(firstYear[i]);
        }
        for(int i=0; i<majorCourses.get(2).size(); i++) {
            secondYear = new String[majorCourses.get(2).size()];
            secondYear[i] = String.valueOf(majorCourses.get(2).get(i));
            System.out.println(secondYear[i]);
        }
        for(int i=0; i<majorCourses.get(3).size(); i++) {
            thirdYear = new String[majorCourses.get(3).size()];
            thirdYear[i] = String.valueOf(majorCourses.get(3).get(i));
            System.out.println(thirdYear[i]);
        }
        for(int i=0; i<majorCourses.get(4).size(); i++) {
            fourthYear = new String[majorCourses.get(4).size()];
            fourthYear[i] = String.valueOf(majorCourses.get(4).get(i));
            System.out.println(fourthYear[i]);
        }
    }

}
