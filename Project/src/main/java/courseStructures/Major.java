package courseStructures;

import courseScraper.mainScraper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Major {
    private LinkedHashMap<String, Course> courses;
    private String name;
    private String[] mechCourses;
    private String[] testCourses;
    private String[] civilCourses;
    private LinkedHashMap<Integer, ArrayList<Course>> civilMajor;


    public Major(String name) {
        setAllCourses(mainScraper.getCourseList());
        this.name = name;
        this.civilMajor = new LinkedHashMap<>();
        this.civilCourses = new String[]{
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
                "CIVL 2000",
                "CIVL 2120",
                "CIVL 2150",
                "CIVL 2160",
                "CIVL 2210",
                "CIVL 2220",
                "CIVL 2240",
                "ENG 2001",
                "ENG 2003",
                //"ESSE 2635",  //doesn't exist in .json
                "MATH 2015",
                "MATH 2271",
                "MATH 2930",
                "CIVL 3110",
                "CIVL 3120",
                "CIVL 3130",
                "CIVL 3140",
                "CIVL 3160",
                "CIVL 3210",
                "CIVL 3220",
                "CIVL 3230",
                "CIVL 3240",
                "CIVL 3260",
                "ENG 3000",
                //"ENVS 2150", //doesn't exist in .json
                "ESSE 2210",
                "CIVL 4110",
                "CIVL 4210",
                "CIVL 4000",
                "CIVL 4001",
                "CIVL 4002",
                "CIVL 4003",
                //"CIVL 4004", //doesn't exist in .json
                "CIVL 4011",
                //"CIVL 4012",  //doesn't exist in .json
                "CIVL 4013",
                //"CIVL 4015",
                //"CIVL 4016",
                "CIVL 4021",
                "CIVL 4022",
                //"CIVL 4023",
                //"CIVL 4024",
                "CIVL 4031",
                "CIVL 4033",
                "CIVL 4034",
                "CIVL 4041",
                "CIVL 4042",
                "CIVL 4043",
                //"CIVL 4044",
        };
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

        this.civilMajor = setMajor(civilCourses);
    }

    public LinkedHashMap<Integer, ArrayList<Course>> setMajor(String[] majorCourses){
        LinkedHashMap<Integer, ArrayList<Course>> major = new LinkedHashMap<>();
        ArrayList<Course> level1 = new ArrayList<>();
        ArrayList<Course> level2 = new ArrayList<>();
        ArrayList<Course> level3 = new ArrayList<>();
        ArrayList<Course> level4 = new ArrayList<>();

        for(int i = 0; i<majorCourses.length; i++){
            Course course = courses.get(majorCourses[i]);
            if(course.getLevel()==1) level1.add(course);
            else if(course.getLevel()==2) level2.add(course);
            else if(course.getLevel()==3) level3.add(course);
            else if(course.getLevel()==4) level4.add(course);
        }
        major.put(1, level1);
        major.put(2, level2);
        major.put(3, level3);
        major.put(4, level4);
        return major;
    }

    public LinkedHashMap<Integer, ArrayList<Course>> getMajor(){
        if(name.equals("Civil Engineering")){
            return civilMajor;
        }
        return null;
    }

    public void setAllCourses(ArrayList<Course> list){
        LinkedHashMap<String, Course> hashMap = new LinkedHashMap<>();
        for (int i= 0; i<list.size(); i++)
            hashMap.put((list.get(i).getCode()), list.get(i));
        this.courses = hashMap;
    }

    public String[] getCivilCourses() {
        return civilCourses;
    }

    public String getName() {
        return name;
    }

    public String[] getMajorCourses(){
        if(this.name.equals("Test")) return testCourses;
        return mechCourses;
    }
}
