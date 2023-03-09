package business.logic.layer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class MajorTest {
    Major civilMajor;
    Major computerMajor;
    Major electricalMajor;
    Major mechanicalMajor;
    HashMap<String, Course> allCourses;
    String[] civilCoursesYear1, civilCoursesYear2, civilCoursesYear3, civilCoursesYear4;
    String[] computerCoursesYear1, computerCoursesYear2, computerCoursesYear3, computerCoursesYear4;
    String[] electricalCoursesYear1, electricalCoursesYear2, electricalCoursesYear3, electricalCoursesYear4;
    String[] mechanicalCoursesYear1, mechanicalCoursesYear2, mechanicalCoursesYear3, mechanicalCoursesYear4;


    @BeforeEach
    void setUp() {
        civilMajor = new Major("Civil Engineering");
        computerMajor = new Major("Computer Engineering");
        electricalMajor = new Major("Electrical Engineering");
        mechanicalMajor = new Major("Mechanical Engineering");
        allCourses = new LinkedHashMap<>();

        civilCoursesYear1 = new String[]{"CHEM 1100", "EECS 1011", "EECS 1021", "ENG 1101", "ENG 1102", "ESSE 1012", "MATH 1013", "MATH 1014", "MATH 1025", "PHYS 1800", "PHYS 1801"};
        civilCoursesYear2 = new String[]{"CIVL 2000", "CIVL 2120", "CIVL 2150", "CIVL 2160", "CIVL 2210", "CIVL 2220", "CIVL 2240", "ENG 2001", "ENG 2003", "MATH 2015", "MATH 2271", "MATH 2930", "ESSE 2210"};
        civilCoursesYear3 = new String[]{"CIVL 3110", "CIVL 3120", "CIVL 3130", "CIVL 3140", "CIVL 3160", "CIVL 3210", "CIVL 3220", "CIVL 3230", "CIVL 3240", "CIVL 3260", "ENG 3000",};
        civilCoursesYear4 = new String[]{"CIVL 4110", "CIVL 4210", "CIVL 4000", "CIVL 4001", "CIVL 4002", "CIVL 4003", "CIVL 4011", "CIVL 4013", "CIVL 4021", "CIVL 4022", "CIVL 4031", "CIVL 4033", "CIVL 4034", "CIVL 4041", "CIVL 4042", "CIVL 4043"};

        computerCoursesYear1 = new String[]{"CHEM 1100", "EECS 1011", "EECS 1021", "EECS 1028", "ENG 1101", "ENG 1102", "MATH 1013", "MATH 1014", "MATH 1025", "PHYS 1800", "PHYS 1801", "MATH 1090"};
        computerCoursesYear2 = new String[]{"EECS 2011", "EECS 2021", "EECS 2030", "EECS 2032", "EECS 2200", "EECS 2210", "ENG 2001", "ENG 2003", "MATH 2015", "MATH 2930", "PHYS 2020", "PHYS 2211", "ESSE 2210"};
        computerCoursesYear3 = new String[]{"ENG 3000", "EECS 3101", "EECS 3201", "EECS 3213", "EECS 3216", "EECS 3221", "EECS 3311", "EECS 3451"};
        computerCoursesYear4 = new String[]{"ENG 4000", "EECS 4201", "EECS 4214", "EECS 4312"};

        electricalCoursesYear1 = new String[]{"CHEM 1100", "EECS 1011", "EECS 1021", "EECS 1028", "ENG 1101", "ENG 1102", "MATH 1013", "MATH 1014", "MATH 1025", "PHYS 1800", "PHYS 1801"};
        electricalCoursesYear2 = new String[]{"EECS 2021", "EECS 2032", "EECS 2200", "EECS 2210", "ENG 2001", "ENG 2003", "MATH 2015", "MATH 2930", "PHYS 2020", "PHYS 2211", "ESSE 2210",};
        electricalCoursesYear3 = new String[]{"EECS 3451", "EECS 3201", "EECS 3604", "EECS 3622", "ENG 3000", "EECS 3603", "EECS 3611", "EECS 3641", "EECS 3216", "EECS 3213", "EECS 3214", "EECS 3221",};
        electricalCoursesYear4 = new String[]{"ENG 4550", "ENG 4000", "EECS 4214", "EECS 4612", "EECS 4614", "EECS 4623", "EECS 4640", "EECS 4643", "EECS 4201", "EECS 4215", "EECS 4404", "EECS 4413", "EECS 4421", "EECS 4422", "EECS 4471", "ENG 4650"};

        mechanicalCoursesYear1 = new String[]{"CHEM 1100", "EECS 1011", "EECS 1021", "ENG 1101", "ENG 1102", "ESSE 1012", "MATH 1013", "MATH 1014", "MATH 1025", "PHYS 1800", "PHYS 1801"};
        mechanicalCoursesYear2 = new String[]{"ENG 2001", "ENG 2003", "MATH 2015", "MATH 2271", "MATH 2930", "MECH 2201", "MECH 2202", "MECH 2301", "MECH 2302", "MECH 2401", "MECH 2412", "MECH 2502", "ESSE 2210", "MECH 2112"};
        mechanicalCoursesYear3 = new String[]{"EECS 3505", "MECH 3201", "MECH 3202", "MECH 3203", "MECH 3302", "MECH 3401", "MECH 3409", "MECH 3502", "MECH 3503", "MECH 3504", "ENG 3000"};
        mechanicalCoursesYear4 = new String[]{"ENG 4000", "ENG 4550", "MECH 4401", "MECH 4402", "MECH 4502", "MECH 4504", "MECH 4201", "MECH 4202", "MECH 4203", "MECH 4301", "MECH 4510", "ENG 4650"};
    }

    @Test
    void getShortCodeCivilTest() {
        for(int i=0; i<civilCoursesYear1.length; i++)
            assertEquals(civilCoursesYear1[i], civilMajor.getShortCode(civilMajor.getMajor().get(1).get(i)));
        for(int i=0; i<civilCoursesYear2.length; i++)
            assertEquals(civilCoursesYear2[i], civilMajor.getShortCode(civilMajor.getMajor().get(2).get(i)));
        for(int i=0; i<civilCoursesYear3.length; i++)
            assertEquals(civilCoursesYear3[i], civilMajor.getShortCode(civilMajor.getMajor().get(3).get(i)));
        for(int i=0; i<civilCoursesYear4.length; i++)
            assertEquals(civilCoursesYear4[i], civilMajor.getShortCode(civilMajor.getMajor().get(4).get(i)));
    }

    @Test
    void getShortCodeComputerTest() {
        for(int i=0; i<computerCoursesYear1.length; i++)
            assertEquals(computerCoursesYear1[i], computerMajor.getShortCode(computerMajor.getMajor().get(1).get(i)));
        for(int i=0; i<computerCoursesYear2.length; i++)
            assertEquals(computerCoursesYear2[i], computerMajor.getShortCode(computerMajor.getMajor().get(2).get(i)));
        for(int i=0; i<computerCoursesYear3.length; i++)
            assertEquals(computerCoursesYear3[i], computerMajor.getShortCode(computerMajor.getMajor().get(3).get(i)));
        for(int i=0; i<computerCoursesYear4.length; i++)
            assertEquals(computerCoursesYear4[i], computerMajor.getShortCode(computerMajor.getMajor().get(4).get(i)));
    }

    @Test
    void getShortCodeElecricalTest() {
        for(int i=0; i<electricalCoursesYear1.length; i++)
            assertEquals(electricalCoursesYear1[i], electricalMajor.getShortCode(electricalMajor.getMajor().get(1).get(i)));
        for(int i=0; i<electricalCoursesYear2.length; i++)
            assertEquals(electricalCoursesYear2[i], electricalMajor.getShortCode(electricalMajor.getMajor().get(2).get(i)));
        for(int i=0; i<electricalCoursesYear3.length; i++)
            assertEquals(electricalCoursesYear3[i], electricalMajor.getShortCode(electricalMajor.getMajor().get(3).get(i)));
        for(int i=0; i<electricalCoursesYear4.length; i++)
            assertEquals(electricalCoursesYear4[i], electricalMajor.getShortCode(electricalMajor.getMajor().get(4).get(i)));
    }

    @Test
    void getShortCodeMechanicalTest() {
        for(int i=0; i<mechanicalCoursesYear1.length; i++)
            assertEquals(mechanicalCoursesYear1[i], mechanicalMajor.getShortCode(mechanicalMajor.getMajor().get(1).get(i)));
        for(int i=0; i<mechanicalCoursesYear2.length; i++)
            assertEquals(mechanicalCoursesYear2[i], mechanicalMajor.getShortCode(mechanicalMajor.getMajor().get(2).get(i)));
        for(int i=0; i<mechanicalCoursesYear3.length; i++)
            assertEquals(mechanicalCoursesYear3[i], mechanicalMajor.getShortCode(mechanicalMajor.getMajor().get(3).get(i)));
        for(int i=0; i<mechanicalCoursesYear4.length; i++)
            assertEquals(mechanicalCoursesYear4[i], mechanicalMajor.getShortCode(mechanicalMajor.getMajor().get(4).get(i)));
    }

    @Test
    void getCourseLevelTest() {
        for(int i=0; i<mechanicalCoursesYear1.length; i++)
            assertEquals(1, mechanicalMajor.getCourseLevel(mechanicalMajor.getMajor().get(1).get(i)));
        for(int i=0; i<computerCoursesYear2.length; i++)
            assertEquals(2, computerMajor.getCourseLevel(computerMajor.getMajor().get(2).get(i)));
        for(int i=0; i<electricalCoursesYear3.length; i++)
            assertEquals(3, electricalMajor.getCourseLevel(electricalMajor.getMajor().get(3).get(i)));
        for(int i=0; i<mechanicalCoursesYear4.length; i++)
            assertEquals(4, mechanicalMajor.getCourseLevel(mechanicalMajor.getMajor().get(4).get(i)));
    }
}