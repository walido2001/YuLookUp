package business.logic.layer;

import java.util.ArrayList;
import java.util.LinkedHashMap;

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
    static Major softMajor = new Major("Software Engineering");
    public static LinkedHashMap<Integer, ArrayList<Course>> softCourses = softMajor.getMajor();
    static Major spaceMajor = new Major("Space Engineering");
    public static LinkedHashMap<Integer, ArrayList<Course>> spaceCourses = spaceMajor.getMajor();

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
                case "software":
                    majorCourses = softCourses;
                case "space":
                    majorCourses = spaceCourses;
                default:
                    break;
            }

//        UserProfile instance = UserProfile.getInstanceOfUserProfile();
//        ArrayList<Course> profileTaken = instance.getRegularCourses();
//        coursesTaken.addAll(profileTaken);
//        System.out.println(profileTaken);

        firstYear = generateYear(majorCourses,coursesTaken,1);
        secondYear = generateYear(majorCourses,coursesTaken,2);
        thirdYear = generateYear(majorCourses, coursesTaken,3);
        fourthYear = generateYear(majorCourses,coursesTaken,4);

    }

    public static ArrayList<Course> generateYear(LinkedHashMap<Integer,ArrayList<Course>> majorCourses, ArrayList<Course> coursesTaken, int year) {
        System.out.println("\n" + year + " Year Courses:");
        ArrayList<Course> listYear = new ArrayList<>(majorCourses.get(year).size());
        for(int i=0; i<majorCourses.get(year).size(); i++) {
            listYear.add(majorCourses.get(year).get(i));
        }
        listYear.removeAll(coursesTaken);
        System.out.println(listYear);
        return listYear;
    }
}
