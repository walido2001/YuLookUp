package business.logic.layer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static business.logic.layer.scheduleGenerator.*;
import static persistence.layer.mainScraper.getCourseList;
import static org.testng.AssertJUnit.*;

public class ScheduleGeneratorTest {

    @Test
    void removeCoursesTaken() {

        ArrayList<Course> courses = getCourseList();

        ArrayList<Course> taken = new ArrayList<>();
        taken.add(courses.get(0));
        taken.add(courses.get(1));
        taken.add(courses.get(2));
        taken.add(courses.get(3));
        taken.add(courses.get(9));
        taken.add(courses.get(10));

        scheduleBuilder("civil", taken);

        boolean contains = false;
        for(int i=0; i< firstYear.size(); i++) {
            if(firstYear.contains(taken)) {
                contains = true;
            }
        }
        assertFalse(contains);
    }

    @Test
    void checkMajorNull() {
        String major = "mechanical";
        ArrayList<Course> courses = getCourseList();

        ArrayList<Course> taken = new ArrayList<>();
        taken.add(courses.get(0));
        assertNotNull(major);
        scheduleBuilder(major, taken);

    }

    @Test
    void checkFirstYear() {
        String major = "civil";
        ArrayList<Course> taken = new ArrayList<>();
        scheduleBuilder(major, taken);
        for(int i=0; i< firstYear.size()-1; i++) {
            int level = firstYear.get(i).getLevel();
            if(level != 1) {
                fail("non-second year course in secondYear courses list");
            }
        }
    }
    @Test
    void checkSecondYear() {
        String major = "civil";
        ArrayList<Course> taken = new ArrayList<>();
        scheduleBuilder(major, taken);
        for(int i=0; i< secondYear.size()-1; i++) {
            int level = secondYear.get(i).getLevel();
            if(level != 2) {
                fail("non-second year course in secondYear courses list");
            }
        }
    }

    @Test
    void checkThirdYear() {
        String major = "civil";
        ArrayList<Course> taken = new ArrayList<>();
        scheduleBuilder(major, taken);
        for(int i=0; i< thirdYear.size()-1; i++) {
            int level = thirdYear.get(i).getLevel();
            if(level != 3) {
                fail("non-third year course in thirdYear courses list");
            }
        }
    }

    @Test
    void checkFourthYear() {
        String major = "civil";
        ArrayList<Course> taken = new ArrayList<>();
        scheduleBuilder(major, taken);
        for(int i=0; i< fourthYear.size()-1; i++) {
            int level = fourthYear.get(i).getLevel();
            if(level != 4) {
                fail("non-fourth year course in fourthYear courses list");
            }
        }
    }

}
