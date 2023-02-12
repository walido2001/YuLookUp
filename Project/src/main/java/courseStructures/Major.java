package courseStructures;

import java.util.ArrayList;
import java.util.HashMap;

public class Major {
    private String name;
    private String[] mechCourses;
    private String[] testCourses;
    private ArrayList<Course> courses;

    public HashMap<String, Course> convertToHashMap(ArrayList<Course> list){
        HashMap<String, Course> hashMap = new HashMap<>();
        for (int i= 0; i<list.size(); i++) {
            String courseCode = list.get(i).getCode();
            hashMap.put(courseCode.substring(3, courseCode.length()-5), list.get(i));
        }
        return hashMap;
    };

    public Major(String name) {
        this.name = name;
        this.testCourses = new String[]{
                "GH 1001",
                "GH 1002",
                "GH 1010",
                "NURS 1512",
                "NURS 1543",
                "GH 2000",
                "GH 2010",
                "KINE 3100",
                "PSYC 3015",
                "PSYC 3495",
                "NURS 4650",
                "PSYC 4050",
                "PSYC 4260",
                "KINE 4740"
        };
        this.mechCourses = new String[]{
                "CHEM 1100",
                "EECS 1011",
                "EECS 1021",
                "ENG 1101",
                "ENG 1102",
                "ESSE 1012",
                "MATH 1013",
                "MATH 1014",
                "MATH 1025",
                "PHYS 1800",
                "PHYS 1801",
                "ENG 2001",
                "ENG 2003",
                "MATH 2015",
                "MATH 2271",
                "MATH 2930",
                "MECH 2201",
                "MECH 2202",
                "MECH 2301",
                "MECH 2302",
                "MECH 2401",
                "MECH 2412",
                "MECH 2502",
                "ENVS 2150",
                "ESSE 2210",
                "MECH 2112",
                "EECS 3505",
                "MECH 3201",
                "MECH 3202",
                "MECH 3203",
                "MECH 3302",
                "MECH 3401",
                "MECH 3409",
                "MECH 3502",
                "MECH 3503",
                "MECH 3504",
                "ENG 3000",
                "ENG 4000",
                "ENG 4550",
                "MECH 4401",
                "MECH 4402",
                "MECH 4502",
                "MECH 4504",
                "MECH 4201",
                "MECH 4202",
                "MECH 4203",
                "MECH 4301",
                "MECH 4510",
                "MECH 4511",
                "MECH 4512",
                "ENG 4650"
        };
    }

    public String getName() {
        return name;
    }

    public String[] getMajorCourses(){
        if(this.name.equals("Test")) return testCourses;
        return mechCourses;
    };
}
