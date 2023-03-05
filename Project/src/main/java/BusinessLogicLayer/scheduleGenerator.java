package BusinessLogicLayer;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static PersistenceLayer.mainScraper.getCourseList;

public class scheduleGenerator {

    public static ArrayList<Course> firstYear;
    public static ArrayList<Course> secondYear;
    public static ArrayList<Course> thirdYear;
    public static ArrayList<Course> fourthYear;

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

        ArrayList<Course> taken = new ArrayList<>();
//        taken.add(courses.get(0));
//        taken.add(courses.get(1));
//        taken.add(courses.get(2));
//        taken.add(courses.get(3));
//        taken.add(courses.get(9));
//        taken.add(courses.get(10));

        System.out.println("taken:\n" + taken + "\n");
        scheduleBuilder("civil", taken);

    }

    public static void scheduleBuilder(String major, ArrayList<Course> coursesTaken) {

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

        System.out.println("\nFirst Year Courses:");
        firstYear = new ArrayList<>(majorCourses.get(1).size());
        for(int i=0; i<majorCourses.get(1).size(); i++) {
            firstYear.add(majorCourses.get(1).get(i));
            firstYear.removeAll(coursesTaken);
        }
        System.out.println(firstYear);

        System.out.println("\nSecond Year Courses:");
        secondYear = new ArrayList<>(majorCourses.get(2).size());
        for(int i=0; i<majorCourses.get(2).size(); i++) {
            secondYear.add(majorCourses.get(2).get(i));
            secondYear.removeAll(coursesTaken);
        }
        System.out.println(secondYear);

        System.out.println("\nThird Year Courses:");
        thirdYear = new ArrayList<>(majorCourses.get(3).size());
        for(int i=0; i<majorCourses.get(3).size(); i++) {
            thirdYear.add(majorCourses.get(3).get(i));
            thirdYear.removeAll(coursesTaken);
        }
        System.out.println(thirdYear);

        System.out.println("\nFourth Year Courses:");
        fourthYear = new ArrayList<>(majorCourses.get(4).size());
        for(int i=0; i<majorCourses.get(4).size(); i++) {
            fourthYear.add(majorCourses.get(4).get(i));
            fourthYear.removeAll(coursesTaken);
        }
        System.out.println(fourthYear);
    }
}
