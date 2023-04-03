package business.logic.layer;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class  scheduleGenerator {

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

    /**
     * This method reads a major, and based on this major depicts which list of courses to use. It also checks for
     * courses inputted as taken by the user in the user profile, then calls the generateYear() method to generate
     * the list of courses pertaining to that major for each year, not including courses taken.
     * @param major is the engineering major inputted by the user
     * @param coursesTaken is the list of courses taken inputted by the user.
     */
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
                    break;
                case "space":
                    majorCourses = spaceCourses;
                    break;
                default:
                    break;
            }

        UserProfile instance = UserProfileInstance.getUserProfile();
        ArrayList<Course> profileTaken = instance.getRegularCourses();
        coursesTaken.addAll(profileTaken);

        firstYear = generateYear(majorCourses,coursesTaken,1);
        secondYear = generateYear(majorCourses,coursesTaken,2);
        thirdYear = generateYear(majorCourses, coursesTaken,3);
        fourthYear = generateYear(majorCourses,coursesTaken,4);

    }

    /**
     * This method takes in a list of courses for a major and organizes them based on year/level. It then uses the
     * coursesTaken parameter to remove courses already taken from this list.
     * @param majorCourses List of courses based on major provided by user
     * @param coursesTaken List of courses taken based on user input
     * @param year Integer representing course level/year (1, 2, 3, 4)
     * @return Returns the list of courses for that year
     */
    public static ArrayList<Course> generateYear(LinkedHashMap<Integer,ArrayList<Course>> majorCourses, ArrayList<Course> coursesTaken, int year) {
        ArrayList<Course> listYear = new ArrayList<>(majorCourses.get(year).size());
        for(int i=0; i<majorCourses.get(year).size(); i++) {
            listYear.add(majorCourses.get(year).get(i));
        }
        listYear.removeAll(coursesTaken);
        return listYear;
    }
}
